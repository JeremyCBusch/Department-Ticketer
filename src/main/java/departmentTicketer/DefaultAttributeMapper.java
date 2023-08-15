package departmentTicketer;

import td.api.Ticket;
import java.util.ArrayList;

/**
 * All of the getters for information required to create tickets in TD.
 *
 * @author Jeremy Busch
 * @since 11/17/22
 */
public class DefaultAttributeMapper {

    /**
     * getFormID():
     * Gets the form ID based on the chosen office.
     *
     * @param officeListVal The chosen office from the oneForm ticket.
     *
     * @return the ID of the form for the dept ticket
     */
    public static int getFormID(int officeListVal) {
        switch (officeListVal) {
            case IDs.ACADEMIC_OFFICE_LIST_VAL:
            case IDs.ACADEMIC_OFFICE_LIST_2_VAL:
                return IDs.ACADEMIC_FORM_ID;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
            case IDs.ACCOUNTING_OFFICE_LIST_2_VAL:
                return IDs.ACCOUNTING_FORM_ID;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
            case IDs.ADMISSIONS_OFFICE_LIST_2_VAL:
                return IDs.ADMISSIONS_FORM_ID;
            case IDs.ADVISING_OFFICE_LIST_VAL:
            case IDs.ADVISING_OFFICE_LIST_2_VAL:
                return IDs.ADVISING_FORM_ID;
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_VAL:
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_2_VAL:
                return IDs.GENERAL_FORM_ID; // Enrollment Services will use this Form
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
            case IDs.FINANCIAL_AID_OFFICE_LIST_2_VAL:
                return IDs.FINANCIAL_AID_FORM_ID;
            case IDs.GENERAL_OFFICE_LIST_VAL:
            case IDs.GENERAL_OFFICE_LIST_2_VAL:
                return IDs.GENERAL_FORM_ID;
            case IDs.IT_OFFICE_LIST_VAL:
            case IDs.IT_OFFICE_LIST_2_VAL:
                return IDs.IT_FORM_ID;
            case IDs.SRR_OFFICE_LIST_VAL:
            case IDs.SRR_OFFICE_LIST_2_VAL:
                return IDs.SRR_FORM_ID;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_2_VAL:
                return IDs.UNIVERSITY_STORE_FORM_ID;
        }
        return 0;
    }

    /**
     * getTypeID:
     * Gets the type ID based on the chosen office.
     *
     * @param officeListVal The chosen office from the oneForm ticket.
     *
     * @return the type ID of the ticket
     */
    public static int getTypeID(int officeListVal) {
        switch (officeListVal) {
            case IDs.ACADEMIC_OFFICE_LIST_VAL:
            case IDs.ACADEMIC_OFFICE_LIST_2_VAL:
                return IDs.ACADEMIC_TYPE_ID;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
            case IDs.ACCOUNTING_OFFICE_LIST_2_VAL:
                return IDs.ACCOUNTING_TYPE_ID;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
            case IDs.ADMISSIONS_OFFICE_LIST_2_VAL:
                return IDs.ADMISSIONS_TYPE_ID;
            case IDs.ADVISING_OFFICE_LIST_VAL:
            case IDs.ADVISING_OFFICE_LIST_2_VAL:
                return IDs.ADVISING_TYPE_ID;
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_VAL:
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_2_VAL:
                return IDs.GENERAL_TYPE_ID; // Enrollment Services will use this Type
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
            case IDs.FINANCIAL_AID_OFFICE_LIST_2_VAL:
                return IDs.FINANCIAL_AID_TYPE_ID;
            case IDs.GENERAL_OFFICE_LIST_VAL:
            case IDs.GENERAL_OFFICE_LIST_2_VAL:
                return IDs.GENERAL_TYPE_ID;
            case IDs.IT_OFFICE_LIST_VAL:
            case IDs.IT_OFFICE_LIST_2_VAL:
                return IDs.IT_TYPE_ID;
            case IDs.SRR_OFFICE_LIST_VAL:
            case IDs.SRR_OFFICE_LIST_2_VAL:
                return IDs.SRR_TYPE_ID;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_2_VAL:
                return IDs.UNIVERSITY_STORE_TYPE_ID;
        }
        return 0;
    }

    /**
     * getTitle:
     * Get the correct title based off of the office.
     *
     * @param officeListVal, The chosen office from the oneForm ticket.
     *
     * @return the correct title for the department ticket
     */

    //USE DEFAULT TITLES FOR FORMS UNLESS KARL SAYS OTHERWISE
    public static String getTitle(int officeListVal) {
        switch (officeListVal) {
            case IDs.ACADEMIC_OFFICE_LIST_VAL:
            case IDs.ACADEMIC_OFFICE_LIST_2_VAL:
                return IDs.ACADEMIC_TITLE;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
            case IDs.ACCOUNTING_OFFICE_LIST_2_VAL:
                return IDs.ACCOUNTING_TITLE;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
            case IDs.ADMISSIONS_OFFICE_LIST_2_VAL:
                return IDs.ADMISSIONS_TITLE;
            case IDs.ADVISING_OFFICE_LIST_VAL:
            case IDs.ADVISING_OFFICE_LIST_2_VAL:
                return IDs.ADVISING_TITLE;
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_VAL:
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_2_VAL:
                return IDs.ENROLLMENT_SERVICES_TITLE; // Enrollment Services Office title
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
            case IDs.FINANCIAL_AID_OFFICE_LIST_2_VAL:
                return IDs.FINANCIAL_AID_TITLE;
            case IDs.GENERAL_OFFICE_LIST_VAL:
            case IDs.GENERAL_OFFICE_LIST_2_VAL:
                return IDs.GENERAL_TITLE;
            case IDs.IT_OFFICE_LIST_VAL:
            case IDs.IT_OFFICE_LIST_2_VAL:
                return IDs.IT_TITLE;
            case IDs.SRR_OFFICE_LIST_VAL:
            case IDs.SRR_OFFICE_LIST_2_VAL:
                return IDs.SRR_TITLE;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_2_VAL:
                return IDs.UNIVERSITY_STORE_TITLE;
        }
        return "";
    }


    /**
     * getAccountID:
     * Here we get the account Id of the oneform and return it.
     *
     * @param oneFormTicket, The oneForm ticket that we will be referencing.
     *
     * @return the AccountID of the ticket (For now same as oneform)
     */
    public static int getAccountID(Ticket oneFormTicket) {
        int accountID;
        accountID = oneFormTicket.getAccountId();
        return accountID;
    }

    /**
     * getStatusID:
     * We use the action taken to find and return the correct status ID
     *
     * @param appID The value of the application ID
     * @param generalActionsVal action options phone attribute from the one form ticket
     *
     * @return the status ID for the department ticket.
     */
    public static int getStatusID(int appID, String generalActionsVal) {
        // This checks if the action option on the ticket is in the arraylist of all action options
        // that should make the dept ticket a new status.

        boolean isStatusNew = IDs.actionOptionsStatusNewChoiceIDs.contains(generalActionsVal);

        switch (appID) {
            case IDs.ACCOUNTING_APPLICATION_ID:
                return ((isStatusNew) ? IDs.ACCOUNTING_STATUS_NEW : IDs.ACCOUNTING_STATUS_CLOSED);
            case IDs.ADMISSIONS_APPLICATION_ID:
                return ((isStatusNew) ? IDs.ADMISSIONS_STATUS_NEW : IDs.ADMISSIONS_STATUS_CLOSED);
            case IDs.ADVISING_APPLICATION_ID:
                return ((isStatusNew) ? IDs.ADVISING_STATUS_NEW : IDs.ADVISING_STATUS_CLOSED);
            case IDs.FINANCIAL_AID_APPLICATION_ID:
                return ((isStatusNew) ? IDs.FINANCIAL_AID_STATUS_NEW : IDs.FINANCIAL_AID_STATUS_CLOSED);
            case IDs.SRR_APPLICATION_ID:
                return ((isStatusNew) ? IDs.SRR_STATUS_NEW : IDs.SRR_STATUS_CLOSED);
            default:
                // Enrollment Services should always hit the default case
                return ((isStatusNew) ? IDs.GENERAL_STATUS_NEW : IDs.GENERAL_STATUS_CLOSED);
        }
    }


    /**
     * getAppId:
     * Here we will get the ID of the application the ticket needs to be sent to.
     *
     * @param officeList1 the office selected by the agent for the first issue
     * @param officeList2 the office selected by the agent for the second issue
     *
     * @return the application ID of the issue.
     */
    public static ArrayList<Integer> getAppIds(int officeList1, int officeList2) {

        ArrayList<Integer> appIDs = new ArrayList<>();

        switch(officeList1) {
            case 0: break;  //if officeList1 wasn't set
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
                appIDs.add(IDs.ACCOUNTING_APPLICATION_ID);
                break;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
                appIDs.add(IDs.ADMISSIONS_APPLICATION_ID);
                break;
            case IDs.ADVISING_OFFICE_LIST_VAL:
                appIDs.add(IDs.ADVISING_APPLICATION_ID);
                break;
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
                appIDs.add(IDs.FINANCIAL_AID_APPLICATION_ID);
                break;
            case IDs.SRR_OFFICE_LIST_VAL:
                appIDs.add(IDs.SRR_APPLICATION_ID);
                break;
            default:
                // Enrollment Office will default everytime to the BYUI-Tickets application
                appIDs.add(IDs.BYUI_TICKETS_APPLICATION_ID);

        }
        switch(officeList2) {
            case 0: break;  //if officeList2 wasn't set
            case IDs.ACCOUNTING_OFFICE_LIST_2_VAL:
                appIDs.add(IDs.ACCOUNTING_APPLICATION_ID);
                break;
            case IDs.ADMISSIONS_OFFICE_LIST_2_VAL:
                appIDs.add(IDs.ADMISSIONS_APPLICATION_ID);
                break;
            case IDs.ADVISING_OFFICE_LIST_2_VAL:
                appIDs.add(IDs.ADVISING_APPLICATION_ID);
                break;
            case IDs.FINANCIAL_AID_OFFICE_LIST_2_VAL:
                appIDs.add(IDs.FINANCIAL_AID_APPLICATION_ID);
                break;
            case IDs.SRR_OFFICE_LIST_2_VAL:
                appIDs.add(IDs.SRR_APPLICATION_ID);
                break;
            default:
                // TODO: Enrollment Office will default everytime to the BYUI-Tickets application
                appIDs.add(IDs.BYUI_TICKETS_APPLICATION_ID);
        }

        return appIDs;
    }


    /**
     * Get Action Options Phones
     * creates an arraylist of the action options phones attribute IDs
     *
     * @param generalActions the bsc tag selected by the agent
     * @param generalActions2 the bsc tag selected by the agent
     *
     * @return the arraylist of action options phones IDs
     */
    public static ArrayList<String> getActionOptionsPhones(String generalActions, String generalActions2) {
        ArrayList<String> actionOptionsPhones = new ArrayList<>();
        actionOptionsPhones.add(0,generalActions);

        if (!generalActions2.equals(""))
            actionOptionsPhones.add(1,generalActions2);

        return actionOptionsPhones;
    }

    /**
     * Get Office List Vals
     * creates an arraylist of the action options phones attribute IDs
     *
     * @param officeList1 the office selected by the agent for the first issue
     * @param officeList2 the office selected by the agent for the second issue
     *
     * @return the arraylist of officeList options phones IDs
     */
    public static ArrayList<Integer> getOfficeListVals(int officeList1, int officeList2){
        ArrayList<Integer> officeListArray = new ArrayList<>();
        officeListArray.add(0,officeList1);

        if (officeList2 != 0)
            officeListArray.add(1,officeList2);

        return officeListArray;
    }



    /**
     * Get Dept Ticket Description
     *
     * @param oneFormTicket the one form used for creating the dept tickets
     * @param deptTicketNum the number of the dept ticket being created
     * @param oneFormDescription the description of the first issue on the one form ticket
     * @param oneFormDescription2 the description of the second issue on the one form ticket
     * @return the proper description of the dept ticket being created
     */
    public static String getDeptTicketDescription(Ticket oneFormTicket, int deptTicketNum, String oneFormDescription, String oneFormDescription2) {
        if (deptTicketNum == 0 || oneFormTicket.getFormId() == IDs.LIVE_CHAT_FORM_ID || oneFormTicket.getFormId() == IDs.EMAIL_FORM_ID)
            return oneFormDescription;
        else
            return oneFormDescription2;
    }

    public static int getSourceID(Ticket oneFormTicket){
        switch(oneFormTicket.getFormId()){
            case IDs.PHONE_FORM_ID:
                return IDs.SOURCE_ID_PHONE;
            case IDs.EMAIL_FORM_ID:
                return IDs.SOURCE_ID_EMAIL;
            default:
                return IDs.SOURCE_ID_LIVE_CHAT;
        }
    }

}
