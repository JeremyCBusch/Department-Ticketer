package departmentTicketer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import td.api.*;
import td.api.Exceptions.TDException;
import td.api.HttpCommunication.ResourceType;
import td.api.Logging.History;
import td.api.Logging.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

import static departmentTicketer.CustomAttributeMapper.*;


/**
 * This is the main driver, here we call all the functions needed to create the new
 *  tickets and upload them to TD (TeamDynamix). We also define the create and upload functions here.
 *
 * @author Jeremy Busch
 * @since 11/17/22
 */

@RestController
public class RequestCollector {
    @Resource(name = "teamDynamix")
    private TeamDynamix TD;
    public static final int ONE_FORM_APPLICATION_ID = 48;
    private static TDLoggingManager log = new TDLoggingManager(false);
    private int reportsRunning = 0;
    private int ticketsRunning = 0;

    @PostMapping(value = "/ping")
    public @ResponseBody
    ResponseEntity<String> ping() {
        System.out.println("pong");
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }


    /**
     * Returns the status of the program
     *
     * @author Robby Breidenbaugh
     * @since 12/21/20
     */
    @RequestMapping(value = "/get-site")
    public @ResponseBody
    ResponseEntity<String> getSiteInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(
            "<h1 style='color : blue;' >Oneform Phone's General Status</h1>" +
            "<b>Reports Running: </b>" + reportsRunning + "<br>" +
            "<b>Tickets Running: </b>" + ticketsRunning + "<br><br>" +
            "<button onclick='reload()'>Reload</button>" +
            "<script>" +
            "console.log(\"Javascript is working\");" +
            "const reload = () => {" +
            "  window.location.reload();" +
            "};" +
            "</script>"
        );
    }

    @RequestMapping(value = "/run-report", params = {"reportID"})
    public @ResponseBody
    String runReport(@RequestParam(value = "reportID") int reportId, @RequestHeader Map<String, String> headers
    ) throws TDException, InterruptedException {
        if (!headers.get("secret").equals(System.getenv("BSC_PROGRAM_SECRET")))
            return "I don't answer to you!";

        reportsRunning++;

        System.out.println("\tRun Report Request ID: " + reportId);
        ArrayList<Map<String, String>> report = TD.getReport(reportId, true, "").getDataRows();

        int counter = 0;
        ResponseEntity<String> response;
        for (Map<String, String> row : report) {
            //Get the ticket in the report
            int ticketId = (int) Float.parseFloat(row.get("TicketID"));

            response = CreateDeptTicket(ticketId, headers);
            counter++;

            System.out.println("\t" + response.getBody());
            System.out.println("\tTicket number " + counter + " of " + report.size() + " complete.");

            if (counter % 60 == 0){
                System.out.println("Waiting 60 seconds to avoid 529 errors.");
                Thread.sleep(60000);
            }
        }
        reportsRunning--;
        return "Run Report Endpoint has Finished";
    }


    /**
     * CreateDeptTickets:
     * This is where the magic happens. On creation we get the ID of the One Form ticket,
     * we then use that to create a Ticketer object that creates all the department tickets
     * and andon cord tickets
     *
     * @param ticketID, the ID of the One Form ticket.
     */
    @RequestMapping(value = "/create-dept-tickets", params = {"ticketID"})
    public @ResponseBody
    ResponseEntity<String> CreateDeptTicket(@RequestParam(value = "ticketID") int ticketID, @RequestHeader Map<String, String> headers){
        History history = new History(ResourceType.TICKET, String.valueOf(ticketID));

        if (headers.get("secret").equals(System.getenv("BSC_PROGRAM_SECRET"))) {
            try {
                TD = new TeamDynamix(System.getenv("TD_API_BASE_URL"), System.getenv("TD_USERNAME"), System.getenv("TD_PASSWORD"), history);
                Ticket oneFormTicket = TD.getTicket(ONE_FORM_APPLICATION_ID, ticketID);
                HashMap<Integer, CustomAttribute> oneFormTicketAttributes = oneFormTicket.getAttributesHashMap();
                Ticketer ticketer;

                boolean editTicket = false;

                // Check if MDT Created Date attribute is present in Ticket
                if (!oneFormTicketAttributes.containsKey(IDs.CREATED_DATE_MDT)) {
                    editTicket = true;
                    Date createdDate = oneFormTicket.getCreatedDate();
                    createdDate.setTime(createdDate.getTime() - (IDs.MILLISECONDS_IN_AN_HOUR * 6)); //compensate for UTC to MDT
                    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                    oneFormTicket.getAttributes().add(new CustomAttribute(IDs.CREATED_DATE_MDT, formatter.format(createdDate)));
                }
                // Check if the BSC Agent attribute is present in Ticket
                if (!oneFormTicketAttributes.containsKey(IDs.BSC_AGENT_ID) && oneFormTicket.getFormId() != IDs.EMAIL_FORM_ID) {
                    editTicket = true;
                    oneFormTicket.getAttributes().add(new CustomAttribute(IDs.BSC_AGENT_ID, oneFormTicket.getCreatedUid()));
                }
                // If custom attributes were added then edit Ticket
                if (editTicket)
                    TD.editTicket(false, oneFormTicket);


                // Check if Office List is abandoned or Spam
                if (officeIsSpamOrAbandoned(oneFormTicketAttributes))
                    history.addEvent(new LoggingEvent("Spam/Abandoned Ticket ID: " + ticketID, "CreateDeptTicket", RequestCollector.class, Level.INFO));
                // Check if there is already a department ticket and if it needs to be updated
                else if (NeedToUpdateDepartmentTicket(TD, oneFormTicket)) {
                    ticketer = new Ticketer(oneFormTicket, history, TD, oneFormTicketAttributes);
                    ticketer.updateDeptTicket(TD, oneFormTicket);
                }
                // Else create new department ticket
                else {
                    ticketer = new Ticketer(oneFormTicket, history, TD, oneFormTicketAttributes);
                    ticketer.createTickets();
                }


                log.logHistory(history);
                return ResponseEntity.status(HttpStatus.OK).body(ticketID + " : The Department Ticketer Has Finished Execution");
            }
            catch (TDException exception) {
                history.addEvent(new LoggingEvent("Ticket ID: " + ticketID + ", " + exception, "CreateDeptTicket", RequestCollector.class, Level.SEVERE));
                log.logHistory(history);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ticketID + " : The Department Ticketer Encountered an Error\n" +
                                exception.getStatusCode() + " Error\n" +
                                exception.getHttpRequestContents()
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
            catch(Exception exception){
                history.addEvent(new LoggingEvent("Ticket ID: " + ticketID + ", " + exception, "CreateDeptTickets", RequestCollector.class, Level.SEVERE));
                log.logHistory(history);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Department Ticketer encountered an exception:" + exception.getMessage());
            }
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("I don't answer to you");
    }

}


