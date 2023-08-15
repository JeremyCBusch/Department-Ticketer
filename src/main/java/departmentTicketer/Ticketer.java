package departmentTicketer;

import td.api.*;
import td.api.Exceptions.TDException;
import td.api.Logging.History;
import td.api.Logging.LoggingEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class Ticketer {
    final private TeamDynamix TD;
    final private Ticket oneFormTicket;
    final private History history;

    //     ****************
    //     Custom Variables
    //     ****************

    //Hashmap attributes
    private HashMap<Integer, CustomAttribute> oneFormAttributes;

    //Offices and Tags
    private int officeList1Attribute = 0;
    private int officeList2Attribute = 0;
    private int BSCTag1 = 0;
    private String BSCTag1Text = "";
    private int BSCTag2 = 0;
    private String BSCTag2Text = "";

    //Descriptions
    private String andonCordNotes = "";
    private String oneFormDescription = "";
    private String oneFormDescription2 = "";

    //Action Options
    private String generalActions = "";
    private String generalActions2 = "";

    // email
    private boolean sendRequestorEmailPrimary = false;
    private boolean sendRequestorEmailCustom = false;
    private String customEmailAddress = "";
    private String CustomEmailBody = "";


    /**
     * NON DEFAULT CONSTRUCTOR
     *
     * @param oneFormTicket, the OneForm ticket that we are getting information from
     * @param history,       the history used to log the paper trail
     * @param TD,            team dynamix object used to create and update tickets
     */
    Ticketer(Ticket oneFormTicket, History history, TeamDynamix TD, HashMap<Integer, CustomAttribute> attributes) {
        this.oneFormTicket = oneFormTicket;
        this.history = history;
        this.TD = TD;
        this.oneFormAttributes = attributes;
        setCustomAttributes();
    }

    /**
     * SET CUSTOM ATTRIBUTES
     *
     * gets all the custom attributes from the one form ticket and assigns them to member variables
     */
    private void setCustomAttributes() {
        CustomAttribute defaultAttribute = new CustomAttribute();

        // We are accessing the custom attributes and getting the value of them and inserting them as member variables of the ticketer class
        // We use the getOrDefault of the hashmap class method because we don't want a null pointer exception if the attribute doesn't exist
        boolean isPhones = oneFormTicket.getFormId() == IDs.PHONE_FORM_ID;

        // Single Issue Attributes
        officeList1Attribute = Integer.parseInt(oneFormAttributes.getOrDefault(IDs.OFFICE_LIST_1_ATTRIBUTE_ID, defaultAttribute).getValue());
        oneFormDescription   = (isPhones ? oneFormAttributes.getOrDefault(IDs.ONE_FORM_DESCRIPTION_ID, defaultAttribute).getValue() : oneFormTicket.getDescription());
        andonCordNotes       = oneFormAttributes.getOrDefault(IDs.ONE_FORM_ANDON_CORD_NOTES_ID, defaultAttribute).getValue();
        BSCTag1              = Integer.parseInt(oneFormAttributes.getOrDefault(IDs.ONE_FORM_TAG_ID, defaultAttribute).getValue());
        BSCTag1Text          = oneFormAttributes.getOrDefault(IDs.ONE_FORM_TAG_ID, defaultAttribute).getValueText();
        generalActions       = oneFormAttributes.get(IDs.ACTIONS_ATTRIBUTE).getValue();

        // Second Issue Attributes
        if (oneFormAttributes.containsKey(IDs.OFFICE_LIST_2_ATTRIBUTE_ID)){
            officeList2Attribute = Integer.parseInt(oneFormAttributes.getOrDefault(IDs.OFFICE_LIST_2_ATTRIBUTE_ID, defaultAttribute).getValue());
            oneFormDescription2  = oneFormAttributes.getOrDefault(IDs.ONE_FORM_DESCRIPTION_2_ID, defaultAttribute).getValue();
            BSCTag2              = Integer.parseInt(oneFormAttributes.getOrDefault(IDs.ONE_FORM_TAG_2_ID, defaultAttribute).getValue());
            BSCTag2Text          = oneFormAttributes.getOrDefault(IDs.ONE_FORM_TAG_2_ID, defaultAttribute).getValueText();
            generalActions2      = oneFormAttributes.getOrDefault(IDs.ACTIONS_ATTRIBUTE_2, defaultAttribute).getValue();
        }

        // Sending Email Attributes
        if (oneFormAttributes.containsKey(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID)) {
            sendRequestorEmailPrimary = (oneFormAttributes.getOrDefault(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID, defaultAttribute).getValue()).contains(IDs.SEND_REQUESTOR_EMAIL_PRIMARY);  // this return a boolean if an option was selected
            sendRequestorEmailCustom  = (oneFormAttributes.getOrDefault(IDs.SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID, defaultAttribute).getValue()).contains(IDs.SEND_REQUESTOR_EMAIL_CUSTOM);   // this return a boolean if an option was selected
            customEmailAddress        = oneFormAttributes.getOrDefault(IDs.CUSTOM_EMAIL_ADDRESS_ATTRIBUTE_ID, defaultAttribute).getValue();
            CustomEmailBody           = addBreakTags(oneFormAttributes.getOrDefault(IDs.CUSTOM_EMAIL_BODY_ATTRIBUTE_ID, defaultAttribute).getValue());
        }
    }

    /**
     * CREATE TICKETS:
     *
     * Gets all the attributes needed for creating the department tickets, andon cord tickets,
     * sending custom emails, and starting conversations with Financial Aid
     */
    public void createTickets() throws TDException {
        ArrayList<Integer> appIDsArray       = DefaultAttributeMapper.getAppIds(officeList1Attribute, officeList2Attribute);
        ArrayList<String>  genActionValArray = DefaultAttributeMapper.getActionOptionsPhones(generalActions, generalActions2);
        ArrayList<Integer> officeListArray   = DefaultAttributeMapper.getOfficeListVals(officeList1Attribute, officeList2Attribute);

        for (int i = 0; i < officeListArray.size(); i++) {
            Ticket deptTicket = new Ticket();
            deptTicket = createDepartmentTicket(deptTicket, oneFormTicket, oneFormAttributes, appIDsArray.get(i), genActionValArray.get(i), officeListArray.get(i), i);
            uploadNewTickets(appIDsArray.get(i), deptTicket, i, history);
        }

        // checks if the ticket needs to create a custom email
        if (sendRequestorEmailPrimary || sendRequestorEmailCustom)
            sendCustomEmail();

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
    private Ticket createDepartmentTicket(Ticket deptTicket, Ticket oneFormTicket, HashMap<Integer, CustomAttribute> attributes, int appID, String generalActions, int officeListVal, int deptTicketNum) {

        setDeptTicketAttributes(deptTicket, oneFormTicket, appID, generalActions, deptTicketNum, officeListVal);
        setDeptTicketCustomAttributes(deptTicket, oneFormTicket, appID, generalActions, deptTicketNum, attributes);

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
     * @param deptTicketNum the number dept ticket being created for the one form ticket
     * @param officeListVal the office selected for the dept ticket
     */
    private void setDeptTicketAttributes(Ticket deptTicket, Ticket oneFormTicket, int appID, String generalActions, int deptTicketNum, int officeListVal) {
        int typeID = DefaultAttributeMapper.getTypeID(officeListVal);
        deptTicket.setAppID(appID);
        deptTicket.setStatusId(DefaultAttributeMapper.getStatusID(appID, generalActions));
        deptTicket.setTypeId(typeID);
        deptTicket.setFormId(DefaultAttributeMapper.getFormID(officeListVal));
        deptTicket.setTitle(DefaultAttributeMapper.getTitle(officeListVal));
        deptTicket.setAccountId(DefaultAttributeMapper.getAccountID(oneFormTicket));
        deptTicket.setPriorityId(IDs.PRIORITY_3_ID);
        deptTicket.setRequestorUid(oneFormTicket.getRequestorUid());

        //THE EXTRA attributes that aren't required by TD
        deptTicket.setDescription(DefaultAttributeMapper.getDeptTicketDescription(oneFormTicket, deptTicketNum, oneFormDescription, oneFormDescription2) +
                                  "<br><br>This ticket was originally received by a support center agent.<br>" +
                                  "<a href='https://td.byui.edu/TDClient/75/Portal/Requests/TicketRequests/TicketDet?TicketID=" + oneFormTicket.getId() + "'>See the original ticket here</a>");
        deptTicket.setLocationId(IDs.LOCATION_ID);
        deptTicket.setSourceId(DefaultAttributeMapper.getSourceID(oneFormTicket));
    }

    /**
     * SET DEPARTMENT TICKET CUSTOM ATTRIBUTES
     *
     * Sets all the default attributes for the dept ticket
     *
     * @param deptTicket the department ticket being created
     * @param oneFormTicket the one form ticket that the dept ticket is using to be created
     * @param appID the application ID for the dept ticket being created
     * @param generalActions The Action Options - Phones Attribute for the dept ticket
     * @param deptTicketNum the number dept ticket being created for the one form ticket
     * @param attributes the custom attributes of the one form ticket
     */
    private void setDeptTicketCustomAttributes(Ticket deptTicket, Ticket oneFormTicket, int appID, String generalActions, int deptTicketNum, HashMap<Integer,CustomAttribute> attributes) {
        ArrayList<CustomAttribute> customAttributes = new ArrayList();

        //sent to level 2 Attribute
        customAttributes.add(new CustomAttribute(CustomAttributeMapper.getSentToLevel2ID(appID), CustomAttributeMapper.getSentToLevel2Value(appID, generalActions)));

        //Add the Agent's name Attribute
        customAttributes.add(new CustomAttribute(CustomAttributeMapper.getAgentNameAttrID(appID), CustomAttributeMapper.getAgentName(oneFormTicket, attributes)));

        //Need to add the string of the tag name to the dept ticket
        customAttributes.add(new CustomAttribute(CustomAttributeMapper.getChoiceTextID(appID), CustomAttributeMapper.getTagText(BSCTag1Text, BSCTag2Text, deptTicketNum)));

        //Add the oneFormTicket ID to the Department Ticket
        customAttributes.add(new CustomAttribute(CustomAttributeMapper.getOneFormTicketIDID(appID), Integer.toString(oneFormTicket.getId())));

        //FA first name initial Attribute
        if (appID == IDs.FINANCIAL_AID_APPLICATION_ID)
            customAttributes.add(new CustomAttribute(IDs.LAST_NAME_INITIAL, CustomAttributeMapper.getLastNameInitialID(oneFormTicket)));

        //If the ticket is admissions then we need to check for override email
        if (appID == IDs.ADMISSIONS_APPLICATION_ID)
            customAttributes.add(new CustomAttribute(IDs.ADMISSIONS_OVERIDE_ID, CustomAttributeMapper.getOverrideEmailID(attributes, deptTicketNum)));

        //If ticket is SRR we need to check for "can't add course"
        if (appID == IDs.SRR_APPLICATION_ID) {
            String onlineOrCampus = CustomAttributeMapper.getCampusOrOnline(attributes, deptTicketNum);
            if (!onlineOrCampus.equals("0"))
                customAttributes.add(new CustomAttribute(IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID, onlineOrCampus));
        }

        //Add the arraylist of custom attributes to the dept ticket
        deptTicket.getAttributes().addAll(customAttributes);
    }

    /**
     * Upload New Tickets
     * This will take the new ticket that has been formed, and create it in the
     * designated application of TD
     *
     * @param appID,          the ticket that we are going to use to update the existing one
     * @param deptTicket,     the ticket that will be put into TD
     * @param deptTicketCount the index of which department ticket we are creating
     */
    private void uploadNewTickets(int appID, Ticket deptTicket, int deptTicketCount, History history) throws TDException {
        deptTicket = TD.createTicket(appID, deptTicket);
        history.addEvent(new LoggingEvent(oneFormTicket.getFormName(),"uploadNewTickets", Ticketer.class , Level.INFO));
        history.addEvent(new LoggingEvent("Created Department Ticket: " + deptTicket.getId() + " | APPID: " + deptTicket.getAppId(), "uploadNewTickets", Ticketer.class, Level.INFO));
        addDeptTicketIDToOneForm(deptTicket, deptTicketCount);
    }

    /**
     * Updates the oneForm ticket with the dept ticket ID
     *
     * @param departmentTicket, the newly created department ticket
     * @param deptTicketCount   the index of which department ticket we are creating
     */
    private void addDeptTicketIDToOneForm(Ticket departmentTicket, int deptTicketCount) throws TDException {
        //Add Department Ticket ID custom attribute to One Form
        CustomAttribute departmentTicketIDCustomAttribute;
        if (deptTicketCount == 0)
            departmentTicketIDCustomAttribute = new CustomAttribute(IDs.DEPARTMENT_TICKET_ID, Integer.toString(departmentTicket.getId()));
        else
            departmentTicketIDCustomAttribute = new CustomAttribute(IDs.DEPARTMENT_TICKET_2_ID, Integer.toString(departmentTicket.getId()));
        oneFormTicket.getAttributes().add(departmentTicketIDCustomAttribute);

        //Update the oneForm ticket
        TD.editTicket(false, this.oneFormTicket);
    }



    /**
     * SEND CUSTOM EMAIL
     *
     * Sends a custom email to the requestor or to a email provided
     *
     */
    private void sendCustomEmail() throws TDException {
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
     * ADD BREAK TAGS
     *
     * Returns a string with break tags instead of new character signs
     *
     * @param str the string that replaces new character signs
     * @return string with break tags or null if the string is null
     */
    private String addBreakTags(String str) { return ((str == null) ? null : String.join("<br>", str.split("\n")));  }



    public static void updateDeptTicket(TeamDynamix TD, Ticket oneFormTicket) throws TDException {
        HashMap<Integer, CustomAttribute> attributeHash = oneFormTicket.getAttributesHashMap();

        // Get the attributes that we need
        int departmentTicketID = Integer.parseInt(attributeHash.get(IDs.DEPARTMENT_TICKET_ID).getValueText());
        int officeListValue = Integer.parseInt(attributeHash.get(IDs.OFFICE_LIST_1_ATTRIBUTE_ID).getValue());
        int appID = DefaultAttributeMapper.getAppIds(officeListValue, 0).get(0);
        String actionOption = attributeHash.getOrDefault(IDs.ACTIONS_ATTRIBUTE, new CustomAttribute()).getValue();
        Ticket deptTicket = TD.getTicket(appID, departmentTicketID);
        ArrayList<CustomAttribute> attributes = deptTicket.getAttributes();

        //Update description and feed
        deptTicket.setDescription(oneFormTicket.getDescription());
        TicketFeedEntry feedEntry = new TicketFeedEntry();
        feedEntry.setComments("Ticket was updated after the customer sent a reply");

        // setting status and sent to level 2
        if (actionOption.equals(IDs.ESCALATE_THIS_TICKET)) {
            deptTicket.setStatusId(DefaultAttributeMapper.getStatusID(appID, attributeHash.get(IDs.ACTIONS_ATTRIBUTE).getValue()));
            deptTicket.setAttributes(editAttribute(attributes, CustomAttributeMapper.getSentToLevel2ID(appID), CustomAttributeMapper.getSentToLevel2Value(appID, actionOption)));
        }

        //Update Ticket
        TD.editTicket(false, deptTicket);
        TD.updateTicket(deptTicket.getAppId(), deptTicket.getId(),feedEntry);
    }

    private static ArrayList<CustomAttribute> editAttribute(ArrayList<CustomAttribute> attributes, int ID, String newValue){
        for (int i = 0; i < attributes.size(); i++){
            if (attributes.get(i).getId() == ID)
                attributes.set(i, new CustomAttribute(attributes.get(i).getId(), newValue));
        }
        return attributes;
    }
}
