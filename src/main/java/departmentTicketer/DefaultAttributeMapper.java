package departmentTicketer;

import td.api.Ticket;

/**
 * All the getters for information required to create tickets in TD.
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
    //Need to update this to different form
    //id = 1679
    public static int getFormID(int officeListVal) {
        switch (officeListVal) {
            case IDs.ACADEMIC_OFFICE_LIST_VAL:
                return IDs.ACADEMIC_FORM_ID;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
                return IDs.ACCOUNTING_FORM_ID;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
                return IDs.ADMISSIONS_FORM_ID;
            case IDs.ADVISING_OFFICE_LIST_VAL:
                return IDs.ADVISING_FORM_ID;
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
                return IDs.FINANCIAL_AID_FORM_ID;
            case IDs.IT_OFFICE_LIST_VAL:
                return IDs.IT_FORM_ID;
            case IDs.SRR_OFFICE_LIST_VAL:
                return IDs.SRR_FORM_ID;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
                return IDs.UNIVERSITY_STORE_FORM_ID;
            case IDs.INTERNATIONAL_SERVICES_LIST_1_VAL:
                return IDs.INTERNATIONAL_SERVICES_FORM_ID;
            default:
                return IDs.GENERAL_FORM_ID;
        }
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
                return IDs.ACADEMIC_TYPE_ID;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
                return IDs.ACCOUNTING_TYPE_ID;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
                return IDs.ADMISSIONS_TYPE_ID;
            case IDs.ADVISING_OFFICE_LIST_VAL:
                return IDs.ADVISING_TYPE_ID;
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
                return IDs.FINANCIAL_AID_TYPE_ID;
            case IDs.IT_OFFICE_LIST_VAL:
                return IDs.IT_TYPE_ID;
            case IDs.SRR_OFFICE_LIST_VAL:
                return IDs.SRR_TYPE_ID;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
                return IDs.UNIVERSITY_STORE_TYPE_ID;
            case IDs.INTERNATIONAL_SERVICES_LIST_1_VAL:
                return IDs.INTERNATIONAL_SERVICES_TYPE_ID;
            default:
                return IDs.GENERAL_TYPE_ID;
        }
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
                return IDs.ACADEMIC_TITLE;
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
                return IDs.ACCOUNTING_TITLE;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
                return IDs.ADMISSIONS_TITLE;
            case IDs.ADVISING_OFFICE_LIST_VAL:
                return IDs.ADVISING_TITLE;
            case IDs.ENROLLMENT_SERVICES_OFFICE_LIST_VAL:
                return IDs.ENROLLMENT_SERVICES_TITLE;
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
                return IDs.FINANCIAL_AID_TITLE;
            case IDs.GENERAL_OFFICE_LIST_VAL:
                return IDs.GENERAL_TITLE;
            case IDs.IT_OFFICE_LIST_VAL:
                return IDs.IT_TITLE;
            case IDs.SRR_OFFICE_LIST_VAL:
                return IDs.SRR_TITLE;
            case IDs.UNIVERSITY_STORE_OFFICE_LIST_VAL:
                return IDs.UNIVERSITY_STORE_TITLE;
            case IDs.INTERNATIONAL_SERVICES_LIST_1_VAL:
                return IDs.INTERNATIONAL_SERVICES_TITLE;
        }
        return "";
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
    // update status to closed for byui tickets application
    public static int getStatusID(int appID, String generalActionsVal) throws Exception {

        boolean isStatusNew = generalActionsVal.equals(IDs.ESCALATE_THIS_TICKET);
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
            case IDs.IT_APPLICATION_ID:
                return ((isStatusNew) ? IDs.IT_STATUS_NEW : IDs.IT_STATUS_CLOSED);
            case IDs.INTERNATIONAL_SERVICES_APP_ID:
                return ((isStatusNew)) ? IDs.INTERNATIONAL_SERVICES_NEW : IDs.INTERNATIONAL_SERVICES_CLOSED;
            default:
                if (isStatusNew) throw new Exception("BYUI tickets dept ticket should not have a status of New");
                return IDs.GENERAL_STATUS_CLOSED;
        }
    }


    /**
     * getAppId:
     * Here we will get the ID of the application the ticket needs to be sent to.
     *
     * @param officeList1 the office selected by the agent for the first issue
     *
     * @return the application ID of the issue.
     */

    //Need to be updated for the application based on the status type.
    // need to find a way to have the IT return either it of general based on
    // Status
    public static int getAppId(int officeList1) {

        switch(officeList1) {
            case 0: return 0;  //if officeList1 wasn't set
            case IDs.ACCOUNTING_OFFICE_LIST_VAL:
                return IDs.ACCOUNTING_APPLICATION_ID;
            case IDs.ADMISSIONS_OFFICE_LIST_VAL:
                return IDs.ADMISSIONS_APPLICATION_ID;
            case IDs.ADVISING_OFFICE_LIST_VAL:
                return IDs.ADVISING_APPLICATION_ID;
            case IDs.FINANCIAL_AID_OFFICE_LIST_VAL:
                return IDs.FINANCIAL_AID_APPLICATION_ID;
            case IDs.SRR_OFFICE_LIST_VAL:
                return IDs.SRR_APPLICATION_ID;
            case IDs.IT_OFFICE_LIST_VAL:
                return IDs.IT_APPLICATION_ID;
            case IDs.INTERNATIONAL_SERVICES_LIST_1_VAL:
                return IDs.INTERNATIONAL_SERVICES_APP_ID;
            default:
                return IDs.BYUI_TICKETS_APPLICATION_ID;
        }
    }

    /**
     * returns the source based on the form ID
     * @param oneFormTicket
     * @return
     */
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
