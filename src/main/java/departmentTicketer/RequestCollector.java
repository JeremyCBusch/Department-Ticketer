package departmentTicketer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import td.api.*;
import td.api.Exceptions.TDException;
import td.api.HttpCommunication.ResourceType;
import td.api.Logging.History;
import td.api.Logging.*;


import java.util.HashMap;
import java.util.logging.Level;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

import static departmentTicketer.Ticketer.updateDeptTicket;



/**
 * This is the main driver, here we call all the functions needed to create the new
 *  tickets and upload them to TD. We also define the create and upload functions here.
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
    ) throws TDException {
        if (!headers.get("secret").equals(System.getenv("BSC_PROGRAM_SECRET")))
            return "I don't answer to you!";

        reportsRunning++;
        History history = new History(ResourceType.NONE, "Temp History");

        TeamDynamix TD = new TeamDynamix(
            System.getenv("TD_API_BASE_URL"),
            System.getenv("TD_USERNAME"),
            System.getenv("TD_PASSWORD"),
            history
        );
        System.out.println("\tRun Report Request ID: " + reportId);
        ArrayList<Map<String, String>> report =
            TD.getReport(reportId, true, "").getDataRows();

        int counter = 1;
        for (Map<String, String> row : report) {
            //Get the ticket in the report
            int ticketId = (int) Float.parseFloat(row.get("TicketID"));
            Ticket ticket = TD.getTicket(48, ticketId);

            //check if a department ticket has been created
            boolean hasDepartmentTicket = false;
            if (ticket.getAttributesHashMap().containsKey(departmentTicketer.IDs.DEPARTMENT_TICKET_ID))
                hasDepartmentTicket = true;

            ResponseEntity<String> response;
            if (!hasDepartmentTicket) {
                response = CreateDeptTickets(ticket.getId(), headers);
                System.out.println("\t" + response.getBody());
            }
            else {
                System.out.println("\tSkipped " + ticketId + "\n\tNo department ticket needed.");
            }
            System.out.println("\tTicket number " + counter + " of " + report.size() + " complete.");
            counter++;

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
    ResponseEntity<String> CreateDeptTickets(@RequestParam(value = "ticketID") int ticketID, @RequestHeader Map<String, String> headers){
        History history = new History(ResourceType.TICKET, String.valueOf(ticketID));

        if (headers.get("secret").equals(System.getenv("BSC_PROGRAM_SECRET"))) {
            try {

                TD = new TeamDynamix(System.getenv("TD_API_BASE_URL"), System.getenv("TD_USERNAME"), System.getenv("TD_PASSWORD"), history);
                TD.login();

                Ticket oneFormTicket = TD.getTicket(ONE_FORM_APPLICATION_ID, ticketID);

                if (!oneFormTicket.getAttributesHashMap().containsKey(IDs.DEPARTMENT_TICKET_ID)){
                    history.addEvent(new LoggingEvent("ONE FORM TICKET ID: " + ticketID, "sendNewTicket", RequestCollector.class, Level.INFO));
                    Ticketer ticketer = new Ticketer(oneFormTicket, history, TD, oneFormTicket.getAttributesHashMap());
                    ticketer.createTickets();

                    log.logHistory(history);
                    Thread.sleep(5000);
                }
                else{
                    history.addEvent(new LoggingEvent("ONE FORM TICKET ID: " + ticketID, "sendNewTicket", RequestCollector.class, Level.INFO));
                    updateDeptTicket(TD, oneFormTicket);
                    history.addEvent(new LoggingEvent("DEPT TICKET DESCRIPTION UPDATED", "sendNewTicket", RequestCollector.class, Level.INFO));
                    log.logHistory(history);

                }
                return ResponseEntity.status(HttpStatus.OK).body(ticketID + " : The Oneform Program Has Finished Execution");
            }
            catch (TDException exception) {
                history.addEvent(new LoggingEvent("Ticket ID: " + ticketID + ", " + exception, "sendNewTicket", RequestCollector.class, Level.SEVERE));
                log.logHistory(history);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ticketID + " : The Oneform Program Encountered an Error\n" +
                                exception.getStatusCode() + " Error\n" +
                                exception.getHttpRequestContents()
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("I don't answer to you");
    }

}


