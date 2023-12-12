package departmentTicketer;

import td.api.CustomAttribute;
import td.api.Exceptions.TDException;
import td.api.TeamDynamix;
import td.api.Ticket;

import java.util.ArrayList;
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
    private static final Map<String, String> initialIDs = new HashMap<>() {{
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
     * @return the ID of the override email action.
     */
    public static String getOverrideEmailID(HashMap<Integer, CustomAttribute> attributes) {
        String admissionsOverride = attributes.getOrDefault(IDs.ADMISSIONS_OVERIDE_ID_ONEFORM, new CustomAttribute()).getValue();

        switch (admissionsOverride) {
            case IDs.ADMISSIONS_OVERIDE_YES_ONEFORM:
                return IDs.ADMISSIONS_OVERIDE_YES;
            case IDs.ADMISSIONS_OVERIDE_NO_ONEFORM:
                return IDs.ADMISSIONS_OVERIDE_NO;
            case IDs.ADMISSIONS_OVERIDE_DOESNT_NEED_ONEFORM:
                return IDs.ADMISSIONS_OVERIDE_DOESNT_NEED;
            default: return "0";
        }
    }


    /**
     * getCampusOrOnline:
     * Get the Id of the choice of the campus or online option
     *
     * @param attributes the attributes of the OneForm Ticket
     * @return The ID of the campus/online attribute as it corresponds to the one form's ID
     */
    public static String getCampusOrOnline(HashMap<Integer, CustomAttribute> attributes) {
        String campOrOnline1 = attributes.getOrDefault(IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID_ONEFORM, new CustomAttribute()).getValue();

        switch (campOrOnline1) {
            case IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID_ONEFORM:
                return IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID;
            case IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID_ONEFORM:
                return IDs.SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID;
            default:
                return "0";
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
            case IDs.IT_APPLICATION_ID:
                return IDs.IT_BSC_AGENT_NAME;
            case IDs.INTERNATIONAL_SERVICES_APP_ID:
                return IDs.INTERNATIONAL_SERVICES_BSC_AGENT_NAME;
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

    //Sent to level2 id for new form = 2281
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
            case IDs.IT_APPLICATION_ID:
                return IDs.IT_SENT_TO_LEVEL_2;
            case IDs.INTERNATIONAL_SERVICES_APP_ID:
                return IDs.INTERNATIONAL_SENT_TO_LEVEL_2;
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
            case IDs.IT_APPLICATION_ID:
                return IDs.IT_ONE_FORM_TAG_NAME_ID;
            case IDs.INTERNATIONAL_SERVICES_APP_ID:
                return IDs.INTERNATIONAL_SERVICES_NAME_ID;
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
            case IDs.IT_APPLICATION_ID:
                return IDs.IT_ONE_FORM_TICKETID_TAG_ID;
            case IDs.INTERNATIONAL_SERVICES_APP_ID:
                return IDs.INTERNATIONAL_SERVICES_ONE_FORM_TICKETID_TAG_ID;
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
    public static String getSentToLevel2Value(int appID, String generalActionsVal) {//throws Exception
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
            case (IDs.IT_APPLICATION_ID):
                return ((isSentToLevel2) ? IDs.IT_SENT_TO_LEVEL_2_YES : IDs.IT_SENT_TO_LEVEL_2_NO);
            case (IDs.INTERNATIONAL_SERVICES_APP_ID):
                return ((isSentToLevel2) ? IDs.INTERNATIONAL_SENT_TO_LEVEL_2_YES : IDs.INTERNATIONAL_SENT_TO_LEVEL_2_NO);
            default:
//                if (isSentToLevel2) throw new Exception("BYUI tickets dept ticket should not have sent to level 2 as \"yes\"");
//                return IDs.GENERAL_SENT_TO_LEVEL_2_NO;
                return ((isSentToLevel2) ? IDs.GENERAL_SENT_TO_LEVEL_2_YES : IDs.GENERAL_SENT_TO_LEVEL_2_NO);
        }
    }

    /**
     * Get Agent Name
     *
     * Returns the UID of the BSC agent custom attribute or who created the ticket
     *
     * @param oneFormTicket
     * @param attributes
     * @return
     */
    public static String getAgentName(Ticket oneFormTicket, HashMap<Integer, CustomAttribute> attributes){
        CustomAttribute defaultAttribute = new CustomAttribute();
        if (oneFormTicket.getFormId() == IDs.LIVE_CHAT_FORM_ID)
            return oneFormTicket.getCreatedFullName();
        else
            return attributes.getOrDefault(IDs.BSC_AGENT_ID, defaultAttribute).getValueText();
    }

    /**
     * One Form Ticket Has Department Ticket
     *
     * checks if the dept ticket ID custom attribute is in the one form ticket
     *
     * @param TD
     * @param ticket
     * @return
     */
    public static boolean NeedToUpdateDepartmentTicket(TeamDynamix TD, Ticket ticket) throws TDException {
        HashMap<Integer, CustomAttribute> attributes = ticket.getAttributesHashMap();

        int deptTicketID = Integer.parseInt(attributes.getOrDefault(IDs.DEPARTMENT_TICKET_ID, new CustomAttribute(0,"0")).getValue());
        if (deptTicketID == 0)
            return false;

        int officeList1 = Integer.parseInt(attributes.get(IDs.OFFICE_LIST_1_ATTRIBUTE_ID).getValue());
        int appID = DefaultAttributeMapper.getAppId(officeList1);
        int deptTicketAppID = Integer.parseInt(attributes.getOrDefault(IDs.DEPT_TICKET_APP_ID_ID, new CustomAttribute(0,"0")).getValue());

        return (deptTicketAppID == appID);    //Checks if the office list has been changed since the dept ticket was created
    }

    /**
     * Office is Spam Or Abandoned
     *
     * Checks if the office list value is spam or abandoned
     * @param oneFormTicketAttributes
     * @return
     */
    public static boolean officeIsSpamOrAbandoned(HashMap<Integer, CustomAttribute> oneFormTicketAttributes) {
        int officeList = Integer.parseInt(oneFormTicketAttributes.get(IDs.OFFICE_LIST_1_ATTRIBUTE_ID).getValue());
        return officeList == IDs.SPAM_OFFICE_LIST_1_VAL || officeList == IDs.ABANDONED_OFFICE_LIST_1_VAL;
    }
    

    public static String generalFormOffice(int officeListVal) {
        switch (officeListVal) {
            case IDs.ACADEMIC_OFFICE_LIST_VAL:
                return IDs.ACADEMIC_OFFICE_LIST_GENERAL_FORM;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
                return IDs.ACCOUNTING_OFFICE_LIST_GENERAL_FORM;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
                return IDs.ADMISSIONS_OFFICE_LIST_GENERAL_FORM;
            case IDs.ADVISING_OFFICE_LIST_VAL:
                return IDs.ADVISING_OFFICE_LIST_GENERAL_FORM;
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_VAL:
                return IDs.ENROLLMENT_SERVICES_OFFICE_LIST_GENERAL_FORM;
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
                return IDs.FINANCIAL_AID_OFFICE_LIST_GENERAL_FORM;
            case IDs.GENERAL_OFFICE_LIST_VAL:
                return IDs.GENERAL_OFFICE_LIST_GENERAL_FORM;
            case IDs.IT_OFFICE_LIST_VAL:
                return IDs.IT_OFFICE_LIST_GENERAL_FORM;
            case IDs.SRR_OFFICE_LIST_VAL:
                return IDs.SRR_OFFICE_LIST_GENERAL_FORM;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
                return IDs.UNIVERSITY_STORE_OFFICE_LIST_GENERAL_FORM;
        }
        return "";
    }


    /**
     * Edits a specified Custom Attribute with a specified value
     * @param attributes
     * @param ID
     * @param newValue
     * @return the edited Custom Attribute ArrayList
     */
    public static ArrayList<CustomAttribute> editAttribute(ArrayList<CustomAttribute> attributes, int ID, String newValue){
        for (int i = 0; i < attributes.size(); i++){
            if (attributes.get(i).getId() == ID)
                attributes.set(i, new CustomAttribute(attributes.get(i).getId(), newValue));
        }
        return attributes;
    }
}
