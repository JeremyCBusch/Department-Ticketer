package departmentTicketer;

import td.api.CustomAttribute;
import td.api.Ticket;
import java.util.HashMap;
import java.util.Map;



/**
 * All the information that is not required to create a ticket in TD,
 * but is needed for BYUI reports and departments.
 *
 * @author Jeremy Busch
 * @since 11/17/22
 */

public class CustomAttributeMapper {

    //These are the IDs for the letters of the initials in the FA form
    private static Map<String, String> initialIDs = new HashMap<>() {{
        put("A", "12509");
        put("B", "12510");
        put("C", "12511");
        put("D", "12535");
        put("E", "12512");
        put("F", "12513");
        put("G", "12514");
        put("H", "12515");
        put("I", "12516");
        put("J", "12517");
        put("K", "12518");
        put("L", "12519");
        put("M", "12520");
        put("N", "12521");
        put("O", "12522");
        put("P", "12523");
        put("Q", "12524");
        put("R", "12525");
        put("S", "12526");
        put("T", "12527");
        put("U", "12528");
        put("V", "12529");
        put("W", "12530");
        put("X", "12531");
        put("Y", "12532");
        put("Z", "12533");
    }};


    /**
     * getLastNameInitial:
     * Gets the first intial of the Requestor's lastname
     *
     * @param oneFormTicket the oneFormTicket
     * @return the first initial of the requestor's lastname
     */
    public static String getLastNameInitialID(Ticket oneFormTicket) {
        String initial = "";
        String initialID;
        String lastName = oneFormTicket.getRequestorLastName();

        if (!oneFormTicket.getRequestorLastName().equals("")) {
            initial = lastName.substring(0, 1);
        }
        initialID = initialIDs.getOrDefault(initial, "");
        return initialID;
    }

    /**
     * getOverrideEmailID:
     * Get the ID of the override email choice
     *
     * @param attributes The attributes of the OneForm Ticket.
     * @param deptTicketNum the number of the dept ticket being created
     * @return the ID of the override email action.
     */
    public static String getOverrideEmailID(HashMap<Integer, CustomAttribute> attributes, int deptTicketNum) {
        String override = "";

        CustomAttribute admissionsOverideAttr1 = attributes.getOrDefault(IDs.ADMISSIONS_OVERIDE_ID_ONEFORM, new CustomAttribute());
        CustomAttribute admissionsOverideAttr2 = attributes.getOrDefault(IDs.ADMISSIONS_OVERIDE_2_ID_ONEFORM, new CustomAttribute());

        if (admissionsOverideAttr1.getValue() != null && deptTicketNum == 0)
            override = admissionsOverideAttr1.getValue();
        else if (admissionsOverideAttr2.getValue() != null && deptTicketNum == 1)
            override = admissionsOverideAttr2.getValue();


        switch (override) {
            case IDs.ADMISSIONS_OVERIDE_YES_ONEFORM:
            case IDs.ADMISSIONS_OVERIDE_YES_2_ONEFORM:
                return IDs.ADMISSIONS_OVERIDE_YES;
            case IDs.ADMISSIONS_OVERIDE_NO_ONEFORM:
            case IDs.ADMISSIONS_OVERIDE_NO_2_ONEFORM:
                return IDs.ADMISSIONS_OVERIDE_NO;
            case IDs.ADMISSIONS_OVERIDE_DOESNT_NEED_ONEFORM:
            case IDs.ADMISSIONS_OVERIDE_DOESNT_NEED_2_ONEFORM:
                return IDs.ADMISSIONS_OVERIDE_DOESNT_NEED;
        }
        return "0";
    }


    /**
     * getCampusOrOnline:
     * Get the Id of the choice of the campus or online option
     *
     * @param attributes the attributes of the OneForm Ticket
     * @param deptTicketNum the number of the dept ticket being created
     * @return The ID of the campus/online attribute as it corresponds to the one form's ID
     */
    public static String getCampusOrOnline(HashMap<Integer, CustomAttribute> attributes, int deptTicketNum) {
        String value = "";

        CustomAttribute campOrOnline1 = attributes.getOrDefault(IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID_ONEFORM, new CustomAttribute());
        CustomAttribute campOrOnline2 = attributes.getOrDefault(IDs.SRR_ONLINE_AND_CAMPUS_2_ATTRIBUTE_ID_ONEFORM, new CustomAttribute());

        if (campOrOnline1.getValue() != null && deptTicketNum == 0)
            value = campOrOnline1.getValue();
        if (campOrOnline2.getValue() != null && deptTicketNum == 1)
            value = campOrOnline2.getValue();

        switch (value) {
            case IDs.SRR_ONLINE_AND_CAMPUS_2_ATTRIBUTE_ON_CAMPUS_ID_ONEFORM:
            case IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID_ONEFORM:
                return IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID;
            case IDs.SRR_ONLINE_AND_CAMPUS_2_ATTRIBUTE_ONLINE_ID_ONEFORM:
            case IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID_ONEFORM:
                return IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID;
        }
        return "0";
    }

    /**
     * getTagText:
     * get the text of the chosen tag on the oneform.
     *
     * @param tag1Text BSC Tag 1 text value
     * @param tag2Text BSC Tag 2 text value
     * @param deptTicketNum the number of the department ticket being created
     * @return the text of the chosen tag on the oneform ticket
     */
    public static String getTagText(String tag1Text, String tag2Text, int deptTicketNum) { return ((deptTicketNum == 0) ? tag1Text : tag2Text); }

    /**
     * getChoiceTextID:
     * get the ID of the Dept Ticket one form Tag text attribute
     *
     * @param appID The application ID of the Dept Ticket
     * @return the ID of the chosen tag on the oneform ticket
     */
    public static int getChoiceTextID(int appID) {
        switch (appID) {
            case IDs.ACCOUNTING_APPLICATION_ID:
                return IDs.ACCOUNTING_ONE_FORM_TAG_NAME_ID;
            case IDs.BYUI_TICKETS_APPLICATION_ID:
                return IDs.BYUI_TICKETS_ONE_FORM_TAG_NAME_ID;
            case IDs.ADMISSIONS_APPLICATION_ID:
                return IDs.ADMISSIONS_ONE_FORM_TAG_NAME_ID;
            case IDs.FINANCIAL_AID_APPLICATION_ID:
                return IDs.FINANCIAL_AID_ONE_FORM_TAG_NAME_ID;
            case IDs.SRR_APPLICATION_ID:
                return IDs.SRR_ONE_FORM_TAG_NAME_ID;
            case IDs.ADVISING_APPLICATION_ID:
                return IDs.ADVISING_ONE_FORM_TAG_NAME_ID;
            default:
                return 0;
        }
    }


    /**
     * getChoiceTextID:
     * get the ID of the Dept Ticket one form Tag text attribute
     *
     * @param appID The application ID of the Dept Ticket
     * @return the ID of the chosen tag on the oneform ticket
     */
    public static int getAgentNameAttrID(int appID) {
        switch (appID) {
            case IDs.ACCOUNTING_APPLICATION_ID:
                return IDs.ACCOUNTING_BSC_AGENT_NAME;
            case IDs.BYUI_TICKETS_APPLICATION_ID:
                return IDs.BYUI_TICKETS_BSC_AGENT_NAME;
            case IDs.ADMISSIONS_APPLICATION_ID:
                return IDs.ADMISSIONS_BSC_AGENT_NAME;
            case IDs.FINANCIAL_AID_APPLICATION_ID:
                return IDs.FINANCIAL_AID_BSC_AGENT_NAME;
            case IDs.SRR_APPLICATION_ID:
                return IDs.SRR_BSC_AGENT_NAME;
            case IDs.ADVISING_APPLICATION_ID:
                return IDs.ADVISING_BSC_AGENT_NAME;
            default:
                return 0;
        }
    }

    /**
     * getSentToLevel2ID:
     * Get the ID of the Sent to Level 2 attribute
     *
     * @param appID The application ID of the Dept Ticket
     * @return the ID of the Sent to Level 2 attribute
     */
    public static int getSentToLevel2ID(int appID) {
        switch (appID) {
            case IDs.ACCOUNTING_APPLICATION_ID:
                return IDs.ACCOUNTING_SENT_TO_LEVEL_2;
            case IDs.BYUI_TICKETS_APPLICATION_ID:
                return IDs.GENERAL_SENT_TO_LEVEL_2;
            case IDs.ADMISSIONS_APPLICATION_ID:
                return IDs.ADMISSIONS_SENT_TO_LEVEL_2;
            case IDs.FINANCIAL_AID_APPLICATION_ID:
                return IDs.FINANCIAL_AID_SENT_TO_LEVEL_2;
            case IDs.SRR_APPLICATION_ID:
                return IDs.SRR_SENT_TO_LEVEL_2;
            case IDs.ADVISING_APPLICATION_ID:
                return IDs.ADVISING_SENT_TO_LEVEL_2;
            default:
                return 0;

        }
    }


    /**
     * getOneFormTicketIDID:
     * Get the ID of the "oneForm Ticket ID" attribute
     *
     * @param appID The application ID of the Dept Ticket
     * @return the ID of the oneForm Ticket ID attribute
     */
    public static int getOneFormTicketIDID(int appID) {
        switch (appID) {
            case IDs.ACCOUNTING_APPLICATION_ID:
                return IDs.ACCOUNTING_ONE_FORM_TICKETID_TAG_ID;
            case IDs.BYUI_TICKETS_APPLICATION_ID:
                return IDs.BYUI_TICKETS_ONE_FORM_TICKETID_TAG_ID;
            case IDs.ADMISSIONS_APPLICATION_ID:
                return IDs.ADMISSIONS_ONE_FORM_TICKETID_TAG_ID;
            case IDs.FINANCIAL_AID_APPLICATION_ID:
                return IDs.FINANCIAL_AID_ONE_FORM_TICKETID_TAG_ID;
            case IDs.SRR_APPLICATION_ID:
                return IDs.SRR_ONE_FORM_TICKETID_TAG_ID;
            case IDs.ADVISING_APPLICATION_ID:
                return IDs.ADVISING_ONE_FORM_TICKETID_TAG_ID;
            default:
                return 0;
        }
    }


    /**
     * getSentToLevel2Value:
     * Gets the ID of the value of the sent to level two attribute choice.
     *
     * @param generalActionsVal, The value of the general actions attribute.
     * @param appID, the application ID of the department ticket
     *
     * @return An ID of the value of the Sent-to-level-two attribute choice.
     */
    public static String getSentToLevel2Value(int appID, String generalActionsVal) {
        // This checks if the action option on the ticket is in the arraylist of all action options that are level 2
        boolean isSentToLevel2 = IDs.actionOptionsLevel2ChoiceIDs.contains(generalActionsVal);

        switch (appID) {
            case (IDs.ACCOUNTING_APPLICATION_ID):
                return ((isSentToLevel2) ? IDs.ACCOUNTING_SENT_TO_LEVEL_2_YES : IDs.ACCOUNTING_SENT_TO_LEVEL_2_NO);
            case (IDs.ADMISSIONS_APPLICATION_ID):
                return ((isSentToLevel2) ? IDs.ADMISSIONS_SENT_TO_LEVEL_2_YES : IDs.ADMISSIONS_SENT_TO_LEVEL_2_NO);
            case (IDs.FINANCIAL_AID_APPLICATION_ID):
                return ((isSentToLevel2) ? IDs.FINANCIAL_AID_SENT_TO_LEVEL_2_YES : IDs.FINANCIAL_AID_SENT_TO_LEVEL_2_NO);
            case (IDs.SRR_APPLICATION_ID):
                return ((isSentToLevel2) ? IDs.SRR_SENT_TO_LEVEL_2_YES : IDs.SRR_SENT_TO_LEVEL_2_NO);
            case (IDs.ADVISING_APPLICATION_ID):
                return ((isSentToLevel2) ? IDs.ADVISING_SENT_TO_LEVEL_2_YES : IDs.ADVISING_SENT_TO_LEVEL_2_NO);
            default:
                // Enrollment Services should default everytime and not be sent to level 2
                return ((isSentToLevel2) ? IDs.GENERAL_SENT_TO_LEVEL_2_YES : IDs.GENERAL_SENT_TO_LEVEL_2_NO);
        }
    }

    public static String getAgentName(Ticket oneFormTicket, HashMap<Integer, CustomAttribute> attributes){
        CustomAttribute defaultAttribute = new CustomAttribute();
        if (oneFormTicket.getFormId() == IDs.EMAIL_FORM_ID)
            return attributes.getOrDefault(IDs.BSC_AGENT_ID, defaultAttribute).getValueText();
        else
            return oneFormTicket.getCreatedFullName();
    }
}
