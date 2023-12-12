package departmentTicketer;

import td.api.*;
import td.api.Exceptions.TDException;
import td.api.Logging.History;
import td.api.Logging.LoggingEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class Emailer {

    private final TeamDynamix TD;
    private final History history;
    private final Ticket oneFormTicket;
    private boolean sendRequestorEmailPrimary = false;
    private boolean sendRequestorEmailCustom = false ;
    private String customEmailAddress = "";
    private String CustomEmailBody = "";
    private String escalationEmail = "";
    private String escalationEmailContactInfo = "";

    public Emailer(Ticket oneFormTicket, History history, TeamDynamix TD){
        HashMap<Integer, CustomAttribute> attributes = oneFormTicket.getAttributesHashMap();
        CustomAttribute defaultAttribute = new CustomAttribute();

        this.oneFormTicket = oneFormTicket;
        this.history       = history;
        this.TD            = TD;

        //Phones Form Emails
        if (attributes.containsKey(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID)) {
            sendRequestorEmailPrimary = (attributes.getOrDefault(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID, defaultAttribute).getValue()).contains(IDs.SEND_REQUESTOR_EMAIL_PRIMARY);  // this return a boolean if an option was selected
            sendRequestorEmailCustom = (attributes.getOrDefault(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID, defaultAttribute).getValue()).contains(IDs.SEND_REQUESTOR_EMAIL_CUSTOM);   // this return a boolean if an option was selected
            customEmailAddress = attributes.getOrDefault(IDs.CUSTOM_EMAIL_ADDRESS_ATTRIBUTE_ID, defaultAttribute).getValue();
            CustomEmailBody = addBreakTags(attributes.getOrDefault(IDs.CUSTOM_EMAIL_BODY_ATTRIBUTE_ID, defaultAttribute).getValue());
        }

        //Email Escalations
        if (attributes.getOrDefault(IDs.ACTIONS_ATTRIBUTE, defaultAttribute).getValue().equals(IDs.ESCALATE_THIS_TICKET_WITH_CUSTOM_EMAIL)) {
            escalationEmail = attributes.getOrDefault(IDs.ESCALATION_EMAIL, defaultAttribute).getValue();
            escalationEmailContactInfo = addBreakTags(attributes.getOrDefault(IDs.ESCALATION_EMAIL_CONTACT_INFORMATION, defaultAttribute).getValue());
        }
    }

    /**
     * SEND CUSTOM EMAIL
     *
     * Sends a custom email to the requestor or to an email provided
     * This is used only in the phones form
     *
     */
    public void sendCustomEmail() throws TDException {
        TicketFeedEntry entry = new TicketFeedEntry();
        entry.setComments(CustomEmailBody);
        ArrayList<String> notifyList = new ArrayList<>();
        if (sendRequestorEmailPrimary) {
            notifyList.add(this.oneFormTicket.getRequestorEmail());
        }
        if (sendRequestorEmailCustom) {
            notifyList.add(customEmailAddress);
        }
        entry.setNotify(notifyList);
        entry.setPrivate(false);
        try {
            TD.updateTicket(this.oneFormTicket.getAppId(), this.oneFormTicket.getId(), entry);
            history.addEvent(new LoggingEvent("Custom Email sent.", "SendNewTicket", RequestCollector.class, Level.INFO));
        }
        catch (Exception e){
            this.oneFormTicket.getAttributes().add(new CustomAttribute(IDs.EMAIL_FAILED_TO_BE_SENT_ID, IDs.EMAIL_FAILED_TO_BE_SENT_YES_ID));
            TD.editTicket(false, this.oneFormTicket);
            history.addEvent(new LoggingEvent("Custom Email failed to be sent", "SendNewTicket", RequestCollector.class, Level.WARNING));
        }

    }

    /**
     * Send Email Escalation
     *
     * Adds a feed entry to the one form ticket and notifies
     * the email address entered by the agent
     *
     * @param appID
     * @param ticketID
     * @throws TDException
     */
    public void sendEmailEscalation(int appID, int ticketID) throws TDException {
        TicketFeedEntry feedEntry = new TicketFeedEntry();
        String emailBody = "Below is an email conversation that one of our agents had with a customer." +
                "<br>Can you please help this person? Their contact information is below:<br>" + escalationEmailContactInfo
                + "<br><br>Best Regards<br>BYUI Support Center<br><br><strong>Email Transcript:</strong><br>" +
                oneFormTicket.getDescription();
        feedEntry.setComments(emailBody);
        ArrayList<String> notifyList = new ArrayList<>(List.of(escalationEmail));
        feedEntry.setNotify(notifyList);

        try {
            TD.updateTicket(appID, ticketID,feedEntry);
            history.addEvent(new LoggingEvent("Sent Escalation Email","Send Email Escalation", Ticketer.class , Level.INFO));
        }
        catch (Exception e){
            history.addEvent(new LoggingEvent("Escalation Email Failed to Send","Send Email Escalation", Ticketer.class , Level.WARNING));
            JsonPatchArray patchData = new JsonPatchArray();
            patchData.addPatchOperation("add","attributes/" + IDs.EMAIL_FAILED_TO_BE_SENT_ID, IDs.EMAIL_FAILED_TO_BE_SENT_YES_ID);
            TD.patchTicket(appID,false, ticketID, patchData);
        }
    }

    /**
     * ADD BREAK TAGS
     *
     * Returns a string with break tags instead of new character signs
     *
     * @param str the string that replaces new character signs
     * @return string with break tags or null if the string is null
     */
    private String addBreakTags(String str) { return ((str == null) ? null : String.join("<br>", str.split("\n")));}
}
