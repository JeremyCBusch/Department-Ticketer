package departmentTicketer;

import td.api.*;
import td.api.Exceptions.TDException;
import td.api.Logging.History;
import td.api.Logging.LoggingEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import static departmentTicketer.CustomAttributeMapper.*;
import static departmentTicketer.DefaultAttributeMapper.*;

public class Ticketer {
    final private TeamDynamix TD;
    final private Ticket oneFormTicket;
    final private History history;
    private HashMap<Integer, CustomAttribute> oneFormAttributes;
    private Emailer emailer;

    //     ****************
    //     Custom Variables
    //     ****************

    //Offices and Tags
    private int officeList1 = 0;
    private int appID = 0;
    private String BSCTag1Text = "";
    private String oneFormDescription = "";
    private String generalAction = "";

    /**
     * NON DEFAULT CONSTRUCTOR
     *
     * @param oneFormTicket, the OneForm ticket that we are getting information from
     * @param history,       the history used to log the paper trail
     * @param TD,            team dynamix object used to create and update tickets
     */
    Ticketer(Ticket oneFormTicket, History history, TeamDynamix TD, HashMap<Integer, CustomAttribute> attributes) {
        CustomAttribute defaultAttribute = new CustomAttribute();
        boolean isPhones = oneFormTicket.getFormId() == IDs.PHONE_FORM_ID;

        this.oneFormTicket      = oneFormTicket;
        this.history            = history;
        this.TD                 = TD;
        this.oneFormAttributes  = attributes;
        this.emailer            = new Emailer(oneFormTicket, history, TD);
        this.officeList1        = Integer.parseInt(oneFormAttributes.getOrDefault(IDs.OFFICE_LIST_1_ATTRIBUTE_ID, defaultAttribute).getValue());
        this.oneFormDescription = (isPhones ? oneFormAttributes.getOrDefault(IDs.ONE_FORM_DESCRIPTION_ID, defaultAttribute).getValue() : oneFormTicket.getDescription());
        this.BSCTag1Text        = oneFormAttributes.getOrDefault(IDs.ONE_FORM_TAG_ID, defaultAttribute).getValueText();
        this.generalAction      = oneFormAttributes.get(IDs.ACTIONS_ATTRIBUTE).getValue();
        this.appID              = getAppId(officeList1);
    }
    /**
     * CREATE TICKETS:
     *
     * Gets all the attributes needed for creating the department tickets, andon cord tickets,
     * sending custom emails, and starting conversations with Financial Aid
     */
    public void createTickets() throws Exception {
        history.addEvent(new LoggingEvent(oneFormTicket.getFormName() + ": " + oneFormTicket.getId(), "CreateDeptTickets", RequestCollector.class, Level.INFO));

        if (!IDs.officesInBYUITickets.contains(officeList1)) {
            Ticket deptTicket = new Ticket();
            deptTicket = createDepartmentTicket(deptTicket, oneFormTicket, oneFormAttributes, appID, generalAction, officeList1);
            uploadNewTickets(appID, deptTicket, history);
        }
        else if (generalAction.equals(IDs.ESCALATE_THIS_TICKET_WITH_CUSTOM_EMAIL))
            emailer.sendEmailEscalation(oneFormTicket.getAppId(), oneFormTicket.getId());
        else
            history.addEvent(new LoggingEvent("Didn't create BYUI tickets department ticket","Create Tickets", Ticketer.class , Level.INFO));

        // checks if the ticket needs a custom email
        if (oneFormAttributes.containsKey(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID))
            emailer.sendCustomEmail();

    }


    /**
     * Create Department Ticket:
     * This will create the new ticket based on information provided from the oneForm ticket
     *
     * @param oneFormTicket, the OneForm ticket that we are getting information from
     * @param appID,         application ID
     * @param attributes,    attributes of the oneForm
     * @return the updated dept ticket
     */
    private Ticket createDepartmentTicket(Ticket deptTicket, Ticket oneFormTicket, HashMap<Integer, CustomAttribute> attributes, int appID, String generalActions, int officeListVal) throws Exception {

        setDeptTicketAttributes(deptTicket, oneFormTicket, appID, generalActions, officeListVal);
        setDeptTicketCustomAttributes(deptTicket, oneFormTicket, appID, generalActions, attributes, officeListVal);

        return deptTicket;
    }
    /**
     * SET DEPARTMENT TICKET ATTRIBUTES:
     *
     * Sets all the default attributes for the dept ticket
     *
     * @param deptTicket the department ticket being created
     * @param oneFormTicket the one form ticket that the dept ticket is using to be created
     * @param appID the application ID for the dept ticket being created
     * @param generalActions The Action Options - Phones Attribute for the dept ticket
     * @param officeListVal the office selected for the dept ticket
     */
    private void setDeptTicketAttributes(Ticket deptTicket, Ticket oneFormTicket, int appID, String generalActions, int officeListVal) throws Exception {
        int typeID = getTypeID(officeListVal);

        deptTicket.setAppID(appID);
        deptTicket.setStatusId(getStatusID(appID, generalActions));
        if(defaultCheckAndSetITtoGeneral(deptTicket, getStatusID(appID, generalActions)) == false)
        {
            deptTicket.setTypeId(typeID);
            deptTicket.setFormId(getFormID(officeListVal));
            deptTicket.setTitle(getTitle(officeListVal));
        }
        deptTicket.setAccountId(oneFormTicket.getAccountId());
        deptTicket.setPriorityId(IDs.PRIORITY_3_ID);
        deptTicket.setRequestorUid(oneFormTicket.getRequestorUid());

        //THE EXTRA attributes that aren't required by TD
        deptTicket.setDescription(oneFormDescription +
                                  "<br><br>This ticket was originally received by a support center agent.<br>" +
                                  "<a href='https://td.byui.edu/TDClient/75/Portal/Requests/TicketRequests/TicketDet?TicketID=" + oneFormTicket.getId() + "'>See the original ticket here</a>");
        deptTicket.setLocationId(IDs.LOCATION_ID);
        deptTicket.setSourceId(getSourceID(oneFormTicket));
    }

    /**
     * SET DEPARTMENT TICKET CUSTOM ATTRIBUTES
     *
     * Sets all the custom attributes for the dept ticket
     *
     * @param deptTicket the department ticket being created
     * @param oneFormTicket the one form ticket that the dept ticket is using to be created
     * @param appID the application ID for the dept ticket being created
     * @param generalActions The Action Options - Phones Attribute for the dept ticket
     * @param attributes the custom attributes of the one form ticket
     * @param officeListVal the office selected for the dept ticket
     */
    private void setDeptTicketCustomAttributes(Ticket deptTicket, Ticket oneFormTicket, int appID, String generalActions, HashMap<Integer,CustomAttribute> attributes, int officeListVal) throws Exception {
        ArrayList<CustomAttribute> customAttributes = new ArrayList();
        int statusId = getStatusID(appID, generalActions);

        if(customCheckAndSetITtoGeneral(deptTicket, statusId, customAttributes,generalActions, attributes,officeListVal) == false)//Sends closed IT tickets to the BYUI tickets application
        {
            customAttributes.add(new CustomAttribute(getSentToLevel2ID(appID), getSentToLevel2Value(appID, generalActions))); //sent to level 2 Attribute
            customAttributes.add(new CustomAttribute(getAgentNameAttrID(appID), getAgentName(oneFormTicket, attributes))); //Add the Agent's name Attribute
            customAttributes.add(new CustomAttribute(getChoiceTextID(appID), BSCTag1Text)); //string of the tag name
            customAttributes.add(new CustomAttribute(getOneFormTicketIDID(appID), Integer.toString(oneFormTicket.getId()))); //oneFormTicket ID in Department Ticket
        }

        if (appID == IDs.FINANCIAL_AID_APPLICATION_ID)
            customAttributes.add(new CustomAttribute(IDs.LAST_NAME_INITIAL, getLastNameInitialID(oneFormTicket))); //FA first name initial Attribute

        if (attributes.get(IDs.BSC_TAG_1_ID).getValue().equals(IDs.OVERRIDE_QUESTIONS))
            customAttributes.add(new CustomAttribute(IDs.ADMISSIONS_OVERIDE_ID, getOverrideEmailID(attributes))); //override email

        if (attributes.get(IDs.BSC_TAG_1_ID).getValue().equals(IDs.CANT_ADD_COURSE_REQUIREMENTS) || attributes.get(IDs.BSC_TAG_1_ID).getValue().equals(IDs.REGISTRATION_CLASS_IS_FULL))
            customAttributes.add(new CustomAttribute(IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID, getCampusOrOnline(attributes))); // online or on campus

        deptTicket.getAttributes().addAll(customAttributes);
    }

    /**
     * Upload New Tickets
     * This will take the new ticket that has been formed, and create it in the
     * designated application of TD
     *
     * @param appID,          the ticket that we are going to use to update the existing one
     * @param deptTicket,     the ticket that will be put into TD
     */
    private void uploadNewTickets(int appID, Ticket deptTicket, History history) throws TDException{
        deptTicket = TD.createTicket(appID, deptTicket);
        addDeptTicketInfoToOneForm(deptTicket.getId(), deptTicket.getAppId());
        uploadAttachments(deptTicket);

        history.addEvent(new LoggingEvent("Created Department Ticket: " +
                deptTicket.getId() + " | APPID: " + deptTicket.getAppId(), "uploadNewTickets", Ticketer.class, Level.INFO));
    }

    /**
     * Updates the oneForm ticket with the dept ticket ID
     *
     * @param departmentTicketID, the newly created department ticket
     */
    private void addDeptTicketInfoToOneForm(int departmentTicketID, int departmentTicketAppID) throws TDException {
        ArrayList<CustomAttribute> attributes = oneFormTicket.getAttributes();
        attributes.add(new CustomAttribute(IDs.DEPARTMENT_TICKET_ID, Integer.toString(departmentTicketID)));
        attributes.add(new CustomAttribute(IDs.DEPT_TICKET_APP_ID_ID, Integer.toString(departmentTicketAppID)));

        TD.editTicket(false, this.oneFormTicket);
    }

    /**
     * Update Dept Ticket
     *
     * Updates the description of the ticket.
     * Updates the status to new and sent to level 2 to yes if needed
     *
     * @param TD
     * @param oneFormTicket
     * @throws Exception
     */
    public void updateDeptTicket(TeamDynamix TD, Ticket oneFormTicket) throws Exception {
        history.addEvent(new LoggingEvent(oneFormTicket.getFormName() + ": " + oneFormTicket.getId(), "updateDeptTicket", Ticketer.class, Level.INFO));
        HashMap<Integer, CustomAttribute> attributeHash = oneFormTicket.getAttributesHashMap();

        // Get the attributes that we need
        int departmentTicketID = Integer.parseInt(attributeHash.get(IDs.DEPARTMENT_TICKET_ID).getValueText());
        int officeListValue = Integer.parseInt(attributeHash.get(IDs.OFFICE_LIST_1_ATTRIBUTE_ID).getValue());
        int appID = getAppId(officeListValue);
        String actionOption = attributeHash.get(IDs.ACTIONS_ATTRIBUTE).getValue();
        String tag = attributeHash.get(IDs.BSC_TAG_1_ID).getValueText();

        Ticket deptTicket = TD.getTicket(appID, departmentTicketID);
        ArrayList<CustomAttribute> attributes = deptTicket.getAttributes();

        deptTicket.setDescription(oneFormDescription +
                "<br><br>This ticket was originally received by a support center agent.<br>" +
                "<a href='https://td.byui.edu/TDClient/75/Portal/Requests/TicketRequests/TicketDet?TicketID=" + oneFormTicket.getId() + "'>See the original ticket here</a>");

        editAttribute(attributes, getChoiceTextID(appID), tag);

        //checking if action option was sent to level 2
        if (IDs.actionOptionsLevel2ChoiceIDs.contains(actionOption)) {
            editAttribute(attributes, getSentToLevel2ID(appID), getSentToLevel2Value(appID, actionOption));
            if (actionOption.equals(IDs.ESCALATE_THIS_TICKET))
                deptTicket.setStatusId(getStatusID(appID, actionOption));
            else if (actionOption.equals(IDs.ESCALATE_THIS_TICKET_WITH_CUSTOM_EMAIL))
                emailer.sendEmailEscalation(oneFormTicket.getAppId(), oneFormTicket.getId());
        }

        TD.editTicket(false, deptTicket);

        // Put feed entry in ticket
        TicketFeedEntry feedEntry = new TicketFeedEntry();
        feedEntry.setComments("Ticket was updated after the customer sent a reply");
        TD.updateTicket(appID, departmentTicketID, feedEntry);
        history.addEvent(new LoggingEvent("Dept Ticket Updated", "CreateDeptTickets", RequestCollector.class, Level.INFO));
    }

    /**
     * Upload Attachments
     *
     * uploads all the attachments from the one form ticket to the dept ticket.
     * If the attachments are too big then it adds a feed entry to the dept ticket
     * letting the agent know there are more attachments on the original ticket
     *
     * @param deptTicket
     * @throws TDException
     */
    private void uploadAttachments(Ticket deptTicket) throws TDException {
        boolean attachmentTooBig = false;
        StringBuilder attachmentLinks = new StringBuilder();
        for (Attachment attachment : oneFormTicket.getAttachments()) {
            if (attachment.getSize() < IDs.apiAttachmentSizeLimit)
                TD.uploadAttachment(deptTicket.getId(), deptTicket.getAppId(), attachment);
            else {
                attachmentTooBig = true;
                attachmentLinks.append(String.format("<a href=\"https://td.byui.edu/TDNext/Apps/Shared/FileOpen?AttachmentID=%s&ItemID=%s&ItemComponent=%s&IsInline=-1\">%s</a><br>", attachment.getId(), attachment.getItemId(), attachment.getAttachmentType(), attachment.getName()));
            }
        }

        if (attachmentTooBig) {
            history.addEvent(new LoggingEvent("Failed to add an attachment to dept ticket", "uploadNewTickets", Ticketer.class, Level.INFO));
            TicketFeedEntry feedEntry = new TicketFeedEntry();
            feedEntry.setComments("Some attachments failed to be added, Here are the links to download them: <br>" + attachmentLinks.toString());
            TD.updateTicket(deptTicket.getAppId(), deptTicket.getId(), feedEntry);
        }
    }

    /**
     * Set's default IT attributes to general
     *
     * Checks if the dept ticket is IT status closed.
     * Sets the member variable app ID to the BYUI tickets
     * Sets the other default variables to BYUI Tickets application
     *
     * @param deptTicket, the TD Ticket object that needs checked
     * @param statusID, the status id returned from the getStatusID() method
     * @return true if IT status closed and code executed, false if not no code executed
     */
    //IT does not want closed tickets in their application, this defines all the default attributes for the dept ticket
    public boolean defaultCheckAndSetITtoGeneral(Ticket deptTicket, int statusID)
    {
        if(statusID == IDs.IT_STATUS_CLOSED)
        {
            this.appID = IDs.BYUI_TICKETS_APPLICATION_ID;

            deptTicket.setAppID(IDs.BYUI_TICKETS_APPLICATION_ID);
            deptTicket.setTypeId(IDs.GENERAL_IT_TYPE_ID);
            deptTicket.setStatusId(IDs.GENERAL_STATUS_CLOSED);
            return true;
        }
        return false;
    }

    /**
     * Set's default IT custom attributes to general
     *
     * Checks if the dept ticket is IT status closed.
     * if true, adds custom attributes for the BYUI tickets application (not IT)
     *
     * @param deptTicket, the TD Ticket object that needs checked
     * @param statusID, the status id returned from the getStatusID() method
     * @param customAttributes, an array list of all the custom attributes to be put in department ticket
     * @param generalActions, The Action Options - Phones Attribute for the dept ticket
     * @param attributes, the custom attributes of the one form ticket
     * @param officeListVal the office selected for the dept ticket
     * @return true if IT status closed and code executed, false if not no code executed
     */
    //IT does not want closed tickets in their application, this defines all the custom attributes for the bsc general form
    public boolean customCheckAndSetITtoGeneral(Ticket deptTicket, int statusID, ArrayList<CustomAttribute> customAttributes, String generalActions, HashMap<Integer,CustomAttribute> attributes, int officeListVal)
    {
        if(statusID == IDs.IT_STATUS_CLOSED)
        {
            this.appID = IDs.BYUI_TICKETS_APPLICATION_ID;
            customAttributes.add(new CustomAttribute(getSentToLevel2ID(IDs.BYUI_TICKETS_APPLICATION_ID), getSentToLevel2Value(IDs.BYUI_TICKETS_APPLICATION_ID, generalActions))); //sent to level 2 Attribute
            customAttributes.add(new CustomAttribute(getAgentNameAttrID(IDs.BYUI_TICKETS_APPLICATION_ID), getAgentName(oneFormTicket, attributes))); //Add the Agent's name Attribute
            customAttributes.add(new CustomAttribute(getChoiceTextID(IDs.BYUI_TICKETS_APPLICATION_ID), BSCTag1Text)); //string of the tag name
            customAttributes.add(new CustomAttribute(getOneFormTicketIDID(IDs.BYUI_TICKETS_APPLICATION_ID), Integer.toString(oneFormTicket.getId())));
            customAttributes.add(new CustomAttribute(IDs.OFFICE_LIST_GENERAL_FORM_ATTRIBUTE_ID, generalFormOffice(officeListVal)));

            return true;
        }
        return false;
    }
}
