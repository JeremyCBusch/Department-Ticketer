package departmentTicketer;

import java.util.ArrayList;
import java.util.List;

/**
 * IDs that are required by the oneForm application.
 *
 * @author Jeremy Busch
 * @since 11/17/22
 */

public class IDs {


    /**********************PRIORITY IDs***********************************************/
    public static final int PRIORITY_3_ID = 21;

    /**********************ATTRIBUTESID***********************************************/
    public static final int ONE_FORM_TAG_ID = 10333;
    public static final int ONE_FORM_TAG_2_ID = 14235;
    public static final int ONE_FORM_ANDON_CORD_NOTES_ID = 10379;
    public static final int ONE_FORM_DESCRIPTION_ID = 13032;
    public static final int ONE_FORM_DESCRIPTION_2_ID = 14241;


    /***********************OFFICE LIST 1 VALUES**********************************/
    // SOURCE -> https://td.byui.edu/TDAdmin/fcb76cab-d63e-41d6-a1f0-34c67299d6bf/48/Attributes/AttributeEdit.aspx?CMPID=9&ATTID=10329

    //The ID of the office radio button
    public static final int OFFICE_LIST_1_ATTRIBUTE_ID = 10329;
    public static final int ACADEMIC_OFFICE_LIST_VAL = 31724;
    public static final int ACCOUNTING_OFFICE_LIST_VAL = 31723;
    public static final int ADMISSIONS_OFFICE_LIST_VAL = 31725;
    public static final int ADVISING_OFFICE_LIST_VAL = 31727;
    public static final int FINANCIAL_AID_OFFICE_LIST_VAL = 31729;
    public static final int GENERAL_OFFICE_LIST_VAL = 31730;
    public static final int IT_OFFICE_LIST_VAL = 31732;
    public static final int SRR_OFFICE_LIST_VAL = 31733;
    public static final int UNIVERSITY_STORE_OFFICE_LIST_VAL = 31734;
    public static final int ENROLLMENT_SERVICES_OFFICE_LIST_VAL = 45065;


    /***********************OFFICE LIST 2 VALUES**********************************/
    // SOURCE -> https://td.byui.edu/TDAdmin/fcb76cab-d63e-41d6-a1f0-34c67299d6bf/48/Attributes/AttributeEdit.aspx?CMPID=9&ATTID=14231

    //The ID of the office 2 radio button
    public static final int OFFICE_LIST_2_ATTRIBUTE_ID = 14231;

    public static final int ACADEMIC_OFFICE_LIST_2_VAL = 43675	;
    public static final int ACCOUNTING_OFFICE_LIST_2_VAL = 43676;
    public static final int ADMISSIONS_OFFICE_LIST_2_VAL = 43677;
    public static final int ADVISING_OFFICE_LIST_2_VAL = 43678;
    public static final int FINANCIAL_AID_OFFICE_LIST_2_VAL = 43680;
    public static final int GENERAL_OFFICE_LIST_2_VAL = 43681;
    public static final int IT_OFFICE_LIST_2_VAL = 43683;
    public static final int SRR_OFFICE_LIST_2_VAL = 43686;
    public static final int UNIVERSITY_STORE_OFFICE_LIST_2_VAL = 43687;
    public static final int ENROLLMENT_SERVICES_OFFICE_LIST_2_VAL = 45066;


    /**********************APP IDs***********************************************/
    public static final int ACCOUNTING_APPLICATION_ID = 54;
    public static final int BYUI_TICKETS_APPLICATION_ID = 40;
    public static final int ADMISSIONS_APPLICATION_ID = 52;
    public static final int FINANCIAL_AID_APPLICATION_ID = 53;
    public static final int SRR_APPLICATION_ID = 55;
    public static final int ADVISING_APPLICATION_ID = 56;

    /************FORM IDs*************************************/
    public static final int ACADEMIC_FORM_ID = 1679;
    public static final int ACCOUNTING_FORM_ID = 1882;
    public static final int ADMISSIONS_FORM_ID = 1792;
    public static final int ADVISING_FORM_ID = 3355;
    public static final int FINANCIAL_AID_FORM_ID = 1881;
    public static final int GENERAL_FORM_ID = 1679; // Enrollment Services Office will use this Form
    public static final int IT_FORM_ID = 2423;
    public static final int SRR_FORM_ID = 1814;
    public static final int UNIVERSITY_STORE_FORM_ID = 1425;
    public static final int PHONE_FORM_ID = 3244;
    public static final int LIVE_CHAT_FORM_ID = 3398;
    public static final int EMAIL_FORM_ID = 3332;

    /***********TYPE IDs**************************************/
    public static final int ACADEMIC_TYPE_ID = 369;
    public static final int ACCOUNTING_TYPE_ID = 568;
    public static final int ADMISSIONS_TYPE_ID = 654;
    public static final int ADVISING_TYPE_ID = 880;
    public static final int FINANCIAL_AID_TYPE_ID = 554;
    public static final int GENERAL_TYPE_ID = 369; // Enrollment Services Office will use this Type
    public static final int IT_TYPE_ID = 702;
    public static final int SRR_TYPE_ID = 666;
    public static final int UNIVERSITY_STORE_TYPE_ID = 373;

    /*****************FORM TITLES*****************************/
    public static final String ACADEMIC_TITLE = "BSC General Contact";
    public static final String ACCOUNTING_TITLE = "BSC Accounting Contact";
    public static final String ADMISSIONS_TITLE = "BSC Admissions Contact";
    public static final String ADVISING_TITLE = "BSC Advising Contact";
    public static final String FINANCIAL_AID_TITLE = "BSC Financial Aid Contact";
    public static final String GENERAL_TITLE = "BSC General Contact";
    public static final String IT_TITLE = "BSC IT Contact";
    public static final String SRR_TITLE = "BSC SRR Contact";
    public static final String UNIVERSITY_STORE_TITLE = "BSC University Store Contact";
    public static final String ENROLLMENT_SERVICES_TITLE = "BSC Enrollment Services Contact";

    /***********STATUS CLOSED**************************************/
    public static final int ACCOUNTING_STATUS_CLOSED = 417;
    public static final int ADMISSIONS_STATUS_CLOSED = 403;
    public static final int ADVISING_STATUS_CLOSED = 431;
    public static final int FINANCIAL_AID_STATUS_CLOSED = 410;
    public static final int GENERAL_STATUS_CLOSED = 200; // Enrollment Services Office will use the status Closed
    public static final int SRR_STATUS_CLOSED = 424;

    /***********STATUS NEW**************************************/
    public static final int ACCOUNTING_STATUS_NEW = 413;
    public static final int ADMISSIONS_STATUS_NEW = 399;
    public static final int ADVISING_STATUS_NEW = 427;
    public static final int FINANCIAL_AID_STATUS_NEW = 406;
    public static final int GENERAL_STATUS_NEW = 196;
    public static final int SRR_STATUS_NEW = 420;

    /***********ACTIONS_ATTRIBUTE**************************************/

    public static final int ACTIONS_ATTRIBUTE = 10500;
    public static final int ACTIONS_ATTRIBUTE_2 = 14242;

    /***********ACTIONS_CHOICES ONE FORM**************************************/
    // CONSOLIDATED ONE FORM ACTION OPTIONS

    public static final String ESCALATED_THE_PHONE_CALL = "33498"; // Source: Phone, Status: CLOSED, Sent to Level 2: Yes
    public static final String ESCALATED_THE_PHONE_CALL_2 = "44128"; // Source: Phone, Status: CLOSED, Sent to Level 2: Yes

    public static final String ESCALATED_AND_RESOLVED_IN_CHAT = "44849"; // Source: Live Chat, Status: CLOSED, Sent to Level 2: Yes

    public static final String ESCALATE_THIS_TICKET = "37226"; // Source: All, Status: NEW, Sent to Level 2: Yes
    public static final String ESCALATE_THIS_TICKET_2 = "44129"; // Source: All, Status: NEW, Sent to Level 2: Yes

    public static final String RESOLVED_WITH_KB_OTHER_RESOURCE = "37467"; // Source: Phone & Live Chat: Status: CLOSED, Sent to Level 2: No
    public static final String RESOLVED_WITH_KB_OTHER_RESOURCE_2 = "44130"; // Source: Phone & Live Chat, Status: CLOSED Sent to Level 2: No

    public static final String CREATED_A_FA_APPOINTMENT = "37218"; // Source: Phone & Live Chat, Status: CLOSED, Sent to Level 2: No
    public static final String CREATED_A_FA_APPOINTMENT_2 = "44132"; // Source: Phone & Live Chat, Status: CLOSED, Sent to Level 2: No

    public static final String REFERRED_TO_PATHWAY = "37222"; // Source: Phone & Live Chat, Status: CLOSED, Sent to Level 2: No
    public static final String REFERRED_TO_PATHWAY_2 = "44138"; // Source: Phone & Live Chat, Status: CLOSED, Sent to Level 2: No

    public static final String RESOLVED_BY_CALLING_ACCOUNTING = "37220"; // Source: Phone, Status CLOSED, Sent to Level 2: Yes
    public static final String RESOLVED_BY_CALLING_ACCOUNTING_2 = "44140"; // Source: Phone, Status CLOSED, Sent to Level 2: Yes

    public static final String REFERRED_TO_IT_AFTER_HOURS_FORM = "37224"; // Source: Phone, Status CLOSED, Sent to Level 2: No
    public static final String REFERRED_TO_IT_AFTER_HOURS_FORM_2 = "44139"; // Source: Phone, Status CLOSED, Sent to Level 2: No

    public static final String SCHEDULED_APPOINTMENT_FOR_STUDENT = "37223"; // Source: Phone & Live Chat, Status: CLOSED, Sent to Level 2: No
    public static final String SCHEDULED_APPOINTMENT_FOR_STUDENT_2 = "44143"; // Source: Phone & Live Chat, Status: CLOSED, Sent to Level 2: No

    public static final String NOTIFY_REQUESTER = "45061"; // Source: Email, Status: CLOSED, Sent to Level 2: No

    public static final String DOESNT_NEED_REPLY = "45063"; // Source: Email, Status: CLOSED, Sent to Level 2: No

    // These arrayLists are used so that we can use a contains method in an if statement
    public static final ArrayList<String> actionOptionsLevel2ChoiceIDs = new ArrayList<>(List.of(
            ESCALATED_THE_PHONE_CALL,
            ESCALATED_THE_PHONE_CALL_2,
            ESCALATED_AND_RESOLVED_IN_CHAT,
            ESCALATE_THIS_TICKET,
            ESCALATE_THIS_TICKET_2,
            RESOLVED_BY_CALLING_ACCOUNTING,
            RESOLVED_BY_CALLING_ACCOUNTING_2
            ));

    public static final ArrayList<String> actionOptionsStatusClosedChoiceIDs = new ArrayList<>(List.of(
            DOESNT_NEED_REPLY,
            NOTIFY_REQUESTER,
            SCHEDULED_APPOINTMENT_FOR_STUDENT,
            SCHEDULED_APPOINTMENT_FOR_STUDENT_2,
            REFERRED_TO_IT_AFTER_HOURS_FORM,
            REFERRED_TO_IT_AFTER_HOURS_FORM_2,
            RESOLVED_BY_CALLING_ACCOUNTING,
            RESOLVED_BY_CALLING_ACCOUNTING_2,
            REFERRED_TO_PATHWAY,
            REFERRED_TO_PATHWAY_2,
            CREATED_A_FA_APPOINTMENT,
            CREATED_A_FA_APPOINTMENT_2,
            RESOLVED_WITH_KB_OTHER_RESOURCE,
            RESOLVED_WITH_KB_OTHER_RESOURCE_2,
            ESCALATED_AND_RESOLVED_IN_CHAT,
            ESCALATED_THE_PHONE_CALL,
            ESCALATED_THE_PHONE_CALL_2
    ));

    public static final ArrayList<String> actionOptionsStatusNewChoiceIDs = new ArrayList<>(List.of(
            ESCALATE_THIS_TICKET, ESCALATE_THIS_TICKET_2));
    /*********************************************************/



    /**********ESCALATE TO FA VIA TEXT ATTRIBUTES*************/

    public static final int FA_CHAT_PHONE_NUMBER = 13348;
    public static final int FA_CHAT_PHONE_NUMBER_2 = 14246;


    /***************SENT TO LEVEL 2 **************************/
    public static final int ACCOUNTING_SENT_TO_LEVEL_2 = 3290;
    public static final String ACCOUNTING_SENT_TO_LEVEL_2_YES = "12813";
    public static final String ACCOUNTING_SENT_TO_LEVEL_2_NO = "12814";

    public static final int ADMISSIONS_SENT_TO_LEVEL_2 = 5183;
    public static final String ADMISSIONS_SENT_TO_LEVEL_2_YES = "15672";
    public static final String ADMISSIONS_SENT_TO_LEVEL_2_NO = "15673";

    public static final int ADVISING_SENT_TO_LEVEL_2 = 11600;
    public static final String ADVISING_SENT_TO_LEVEL_2_YES = "36274";
    public static final String ADVISING_SENT_TO_LEVEL_2_NO = "36275";

    public static final int FINANCIAL_AID_SENT_TO_LEVEL_2 = 3275;
    public static final String FINANCIAL_AID_SENT_TO_LEVEL_2_YES = "12507";
    public static final String FINANCIAL_AID_SENT_TO_LEVEL_2_NO = "12508";

    public static final int GENERAL_SENT_TO_LEVEL_2 = 2281;
    public static final String GENERAL_SENT_TO_LEVEL_2_YES = "5919";
    public static final String GENERAL_SENT_TO_LEVEL_2_NO = "5920";

    public static final int SRR_SENT_TO_LEVEL_2 = 5467;
    public static final String SRR_SENT_TO_LEVEL_2_YES = "15872";
    public static final String SRR_SENT_TO_LEVEL_2_NO = "15873";


    /************************LAST NAME INTIAL?*************/
    public static final int LAST_NAME_INITIAL = 3276;

    /************************DEPARTMENT TICKET ID ATTRIBUTE*************/
    public static final int DEPARTMENT_TICKET_ID = 11452;
    public static final int DEPARTMENT_TICKET_2_ID = 14256;


    /****************BYUI SUPPORT CENTER LOCATION************/

    public static final int LOCATION_ID = 1;

    /****************SOURCE ID******************************/

    public static final int SOURCE_ID_PHONE = 5;
    public static final int SOURCE_ID_EMAIL = 6;
    public static final int SOURCE_ID_LIVE_CHAT = 11;

    /*********************OVERRIDE IDS**********************/

    public static final int ADMISSIONS_OVERIDE_ID_ONEFORM = 10943;
    public static final int ADMISSIONS_OVERIDE_2_ID_ONEFORM = 14314;
    public static final String ADMISSIONS_OVERIDE_YES_ONEFORM = "34016";
    public static final String ADMISSIONS_OVERIDE_YES_2_ONEFORM = "44262";
    public static final String ADMISSIONS_OVERIDE_NO_ONEFORM = "34017";
    public static final String ADMISSIONS_OVERIDE_NO_2_ONEFORM = "44263";
    public static final String ADMISSIONS_OVERIDE_DOESNT_NEED_ONEFORM = "34018";
    public static final String ADMISSIONS_OVERIDE_DOESNT_NEED_2_ONEFORM = "44264";


    public static final int ADMISSIONS_OVERIDE_ID = 8171;
    public static final String ADMISSIONS_OVERIDE_YES = "22635";
    public static final String ADMISSIONS_OVERIDE_NO = "22636";
    public static final String ADMISSIONS_OVERIDE_DOESNT_NEED = "32854";


    /*******************ONE FORM TAG NAME******************/

    public static final int ACCOUNTING_ONE_FORM_TAG_NAME_ID = 10944;
    public static final int BYUI_TICKETS_ONE_FORM_TAG_NAME_ID = 10945;
    public static final int ADMISSIONS_ONE_FORM_TAG_NAME_ID = 10946;
    public static final int FINANCIAL_AID_ONE_FORM_TAG_NAME_ID = 10947;
    public static final int SRR_ONE_FORM_TAG_NAME_ID = 10948;
    public static final int ADVISING_ONE_FORM_TAG_NAME_ID = 11602;


    /*******************ONE FORM TICKET ID******************/

    public static final int ACCOUNTING_ONE_FORM_TICKETID_TAG_ID = 11013;
    public static final int BYUI_TICKETS_ONE_FORM_TICKETID_TAG_ID = 11015;
    public static final int ADMISSIONS_ONE_FORM_TICKETID_TAG_ID = 11014;
    public static final int FINANCIAL_AID_ONE_FORM_TICKETID_TAG_ID = 11016;
    public static final int SRR_ONE_FORM_TICKETID_TAG_ID = 11017;
    public static final int ADVISING_ONE_FORM_TICKETID_TAG_ID = 11601;


    /*********************SRR EXTRA ATTRIBUTES AND ON ONEFORM*************/

    public static final int SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID = 9829;
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID = "30087";
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID = "30088";

    public static final int SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID_ONEFORM = 11044;
    public static final int SRR_ONLINE_AND_CAMPUS_2_ATTRIBUTE_ID_ONEFORM = 14315;

    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID_ONEFORM = "34478";
    public static final String SRR_ONLINE_AND_CAMPUS_2_ATTRIBUTE_ON_CAMPUS_ID_ONEFORM = "44265";
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID_ONEFORM = "34479";
    public static final String SRR_ONLINE_AND_CAMPUS_2_ATTRIBUTE_ONLINE_ID_ONEFORM = "44266";


    /*******************DEPARTMENT TICKET BSC AGENT NAME ATTRIBUTES******************/

    public static final int ACCOUNTING_BSC_AGENT_NAME = 5514;
    public static final int BYUI_TICKETS_BSC_AGENT_NAME = 5520;
    public static final int ADMISSIONS_BSC_AGENT_NAME = 5871;
    public static final int FINANCIAL_AID_BSC_AGENT_NAME = 5523;
    public static final int SRR_BSC_AGENT_NAME = 5533;
    public static final int ADVISING_BSC_AGENT_NAME = 11603;



    /*******************EMAIL REQUESTOR ATTRIBUTE IDS******************/

    public static final int SEND_REQUESTOR_EMAIL_ATTRIBUTE_ID = 12697;
    public static final String SEND_REQUESTOR_EMAIL_PRIMARY = "38916";
    public static final String SEND_REQUESTOR_EMAIL_CUSTOM = "39146";
    public static final int CUSTOM_EMAIL_ADDRESS_ATTRIBUTE_ID = 12777;
    public static final int CUSTOM_EMAIL_BODY_ATTRIBUTE_ID = 12698;
    public static final int EMAIL_FAILED_TO_BE_SENT_ID = 14795;
    public static final String EMAIL_FAILED_TO_BE_SENT_YES_ID = "45146";


    /*******************EMAIL Form IDs******************/
    public static final int BSC_AGENT_ID = 11942;
}
