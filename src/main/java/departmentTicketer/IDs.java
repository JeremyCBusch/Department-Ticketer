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

    public static final int MILLISECONDS_IN_AN_HOUR = 3600000;

    /**********************PRIORITY IDs***********************************************/
    public static final int PRIORITY_3_ID = 21;

    /**********************ATTRIBUTESID***********************************************/
    public static final int ONE_FORM_TAG_ID = 10333;
    public static final int ONE_FORM_DESCRIPTION_ID = 13032;
    public static final int CREATED_DATE_MDT = 15283;

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
    public static final int ABANDONED_OFFICE_LIST_1_VAL = 44800;
    public static final int SPAM_OFFICE_LIST_1_VAL = 36076;
    public static final int DEPT_TICKET_APP_ID_ID = 15191;
    public static final int INTERNATIONAL_SERVICES_LIST_1_VAL = 47026;

    /**********************APP IDs***********************************************/
    public static final int ACCOUNTING_APPLICATION_ID = 54;
    public static final int BYUI_TICKETS_APPLICATION_ID = 40;
    public static final int ADMISSIONS_APPLICATION_ID = 52;
    public static final int FINANCIAL_AID_APPLICATION_ID = 53;
    public static final int SRR_APPLICATION_ID = 55;
    public static final int ADVISING_APPLICATION_ID = 56;
    public static final int IT_APPLICATION_ID = 33;
    public static final int INTERNATIONAL_SERVICES_APP_ID = 69;

    /************FORM IDs*************************************/
    public static final int ACADEMIC_FORM_ID = 1679;
    public static final int ACCOUNTING_FORM_ID = 1882;
    public static final int ADMISSIONS_FORM_ID = 1792;
    public static final int ADVISING_FORM_ID = 3355;
    public static final int FINANCIAL_AID_FORM_ID = 1881;
    public static final int GENERAL_FORM_ID = 1679; // Enrollment Services Office will use this Form
    public static final int IT_FORM_ID = 3110; // previously 2423
    public static final int SRR_FORM_ID = 1814;
    public static final int UNIVERSITY_STORE_FORM_ID = 1425;
    public static final int PHONE_FORM_ID = 3244;
    public static final int EMAIL_FORM_ID = 3332;
    public static final int LIVE_CHAT_FORM_ID = 3398;

    public static final int INTERNATIONAL_SERVICES_FORM_ID = 3879;

    /***********TYPE IDs**************************************/
    public static final int ACADEMIC_TYPE_ID = 369;
    public static final int ACCOUNTING_TYPE_ID = 568;
    public static final int ADMISSIONS_TYPE_ID = 654;
    public static final int ADVISING_TYPE_ID = 880;
    public static final int FINANCIAL_AID_TYPE_ID = 554;
    public static final int GENERAL_TYPE_ID = 369; // Enrollment Services Office will use this Type
    public static final int GENERAL_IT_TYPE_ID = 702;
    public static final int IT_TYPE_ID = 328;
    public static final int SRR_TYPE_ID = 666;
    public static final int UNIVERSITY_STORE_TYPE_ID = 373;

    public static final int INTERNATIONAL_SERVICES_TYPE_ID = 1020;

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

    public static final String INTERNATIONAL_SERVICES_TITLE = "BSC International Services Contact";

    /***********STATUS CLOSED**************************************/
    public static final int ACCOUNTING_STATUS_CLOSED = 417;
    public static final int ADMISSIONS_STATUS_CLOSED = 403;
    public static final int ADVISING_STATUS_CLOSED = 431;
    public static final int FINANCIAL_AID_STATUS_CLOSED = 410;
    public static final int GENERAL_STATUS_CLOSED = 200; // Enrollment Services Office will use the status Closed
    public static final int SRR_STATUS_CLOSED = 424;
    public static final int IT_STATUS_CLOSED = 56;

    public static final int INTERNATIONAL_SERVICES_CLOSED = 1092;

    /***********STATUS NEW**************************************/
    public static final int ACCOUNTING_STATUS_NEW = 413;
    public static final int ADMISSIONS_STATUS_NEW = 399;
    public static final int ADVISING_STATUS_NEW = 427;
    public static final int FINANCIAL_AID_STATUS_NEW = 406;
    public static final int SRR_STATUS_NEW = 420;
    public static final int IT_STATUS_NEW = 52;
    public static final int INTERNATIONAL_SERVICES_NEW = 1088;

    /***********ACTIONS_ATTRIBUTE**************************************/

    public static final int ACTIONS_ATTRIBUTE = 10500;

    /***********ACTIONS_CHOICES ONE FORM**************************************/
    // CONSOLIDATED ONE FORM ACTION OPTIONS
    public static final String ESCALATED_THE_PHONE_CALL = "33498"; // Source: Phone, Status: CLOSED, Sent to Level 2: Yes
    public static final String ESCALATED_AND_RESOLVED_IN_CHAT = "44849"; // Source: Live Chat, Status: CLOSED, Sent to Level 2: Yes
    public static final String ESCALATE_THIS_TICKET = "37226"; // Source: All, Status: NEW, Sent to Level 2: Yes
    public static final String ESCALATE_THIS_TICKET_WITH_CUSTOM_EMAIL = "45253";
    public static final String RESOLVED_BY_CALLING_ACCOUNTING = "37220"; // Source: Phone, Status CLOSED, Sent to Level 2: Yes

    // These arrayLists are used so that we can use a contains method in an if statement
    public static final ArrayList<String> actionOptionsLevel2ChoiceIDs = new ArrayList<>(List.of(
            ESCALATED_THE_PHONE_CALL,
            ESCALATED_AND_RESOLVED_IN_CHAT,
            ESCALATE_THIS_TICKET,
            RESOLVED_BY_CALLING_ACCOUNTING,
            ESCALATE_THIS_TICKET_WITH_CUSTOM_EMAIL
            ));
    public static final ArrayList<Integer> officesInBYUITickets = new ArrayList<>(List.of(
            ACADEMIC_OFFICE_LIST_VAL, GENERAL_OFFICE_LIST_VAL, UNIVERSITY_STORE_OFFICE_LIST_VAL, ENROLLMENT_SERVICES_OFFICE_LIST_VAL));

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
    public static final String GENERAL_SENT_TO_LEVEL_2_NO = "5920";
    public static final String GENERAL_SENT_TO_LEVEL_2_YES = "5919";
    public static final int SRR_SENT_TO_LEVEL_2 = 5467;
    public static final String SRR_SENT_TO_LEVEL_2_YES = "15872";
    public static final String SRR_SENT_TO_LEVEL_2_NO = "15873";
    public static final int IT_SENT_TO_LEVEL_2 = 14782;
    public static final String IT_SENT_TO_LEVEL_2_YES = "45144";
    public static final String IT_SENT_TO_LEVEL_2_NO = "45145";

    public static final int INTERNATIONAL_SENT_TO_LEVEL_2 = 15286;

    public static final String INTERNATIONAL_SENT_TO_LEVEL_2_YES = "47028";

    public static final String INTERNATIONAL_SENT_TO_LEVEL_2_NO = "47029";



    /************************LAST NAME INTIAL?*************/
    public static final int LAST_NAME_INITIAL = 3276;
    /************************DEPARTMENT TICKET ID ATTRIBUTE*************/
    public static final int DEPARTMENT_TICKET_ID = 11452;
    /****************BYUI SUPPORT CENTER LOCATION************/
    public static final int LOCATION_ID = 1;
    /****************SOURCE ID******************************/
    public static final int SOURCE_ID_PHONE = 5;
    public static final int SOURCE_ID_EMAIL = 6;
    public static final int SOURCE_ID_LIVE_CHAT = 11;
    /*********************OVERRIDE IDS**********************/
    public static final int ADMISSIONS_OVERIDE_ID_ONEFORM = 10943;
    public static final String ADMISSIONS_OVERIDE_YES_ONEFORM = "34016";
    public static final String ADMISSIONS_OVERIDE_NO_ONEFORM = "34017";
    public static final String ADMISSIONS_OVERIDE_DOESNT_NEED_ONEFORM = "34018";
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
    public static final int IT_ONE_FORM_TAG_NAME_ID = 14783;

    public static final int INTERNATIONAL_SERVICES_NAME_ID = 15288;

    /*******************BSC TAG 1*******************/
    public static final int BSC_TAG_1_ID = 10333;
    public static final String CANT_ADD_COURSE_REQUIREMENTS = "31831";
    public static final String REGISTRATION_CLASS_IS_FULL = "31829";
    public static final String OVERRIDE_QUESTIONS = "31985";

    /*******************ONE FORM TICKET ID******************/
    public static final int ACCOUNTING_ONE_FORM_TICKETID_TAG_ID = 11013;
    public static final int BYUI_TICKETS_ONE_FORM_TICKETID_TAG_ID = 11015;
    public static final int ADMISSIONS_ONE_FORM_TICKETID_TAG_ID = 11014;
    public static final int FINANCIAL_AID_ONE_FORM_TICKETID_TAG_ID = 11016;
    public static final int SRR_ONE_FORM_TICKETID_TAG_ID = 11017;
    public static final int ADVISING_ONE_FORM_TICKETID_TAG_ID = 11601;
    public static final int IT_ONE_FORM_TICKETID_TAG_ID = 14784;

    public static final int INTERNATIONAL_SERVICES_ONE_FORM_TICKETID_TAG_ID = 15289;

    /*********************SRR EXTRA ATTRIBUTES AND ON ONEFORM*************/
    public static final int SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID = 9829;
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID = "30087";
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID = "30088";
    public static final int SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ID_ONEFORM = 11044;
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ON_CAMPUS_ID_ONEFORM = "34478";
    public static final String SRR_ONLINE_AND_CAMPUS_ATTRIBUTE_ONLINE_ID_ONEFORM = "34479";

    /*******************DEPARTMENT TICKET BSC AGENT NAME ATTRIBUTES******************/
    public static final int ACCOUNTING_BSC_AGENT_NAME = 5514;
    public static final int BYUI_TICKETS_BSC_AGENT_NAME = 5520;
    public static final int ADMISSIONS_BSC_AGENT_NAME = 5871;
    public static final int FINANCIAL_AID_BSC_AGENT_NAME = 5523;
    public static final int SRR_BSC_AGENT_NAME = 5533;
    public static final int ADVISING_BSC_AGENT_NAME = 11603;
    public static final int IT_BSC_AGENT_NAME = 5528;

    public static final int INTERNATIONAL_SERVICES_BSC_AGENT_NAME = 15287;

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
    public static final int ESCALATION_EMAIL = 14830;
    public static final int ESCALATION_EMAIL_CONTACT_INFORMATION = 14831;

    /*******************EMAIL Form IDs******************/
    public static final int apiAttachmentSizeLimit = 4194304;

    /***********BSC GENERAL FORM IDs**************************************/
    public static final int OFFICE_LIST_GENERAL_FORM_ATTRIBUTE_ID = 10091;
    public static final String ACADEMIC_OFFICE_LIST_GENERAL_FORM = "45988";
    public static final String ACCOUNTING_OFFICE_LIST_GENERAL_FORM = "30827";
    public static final String ADMISSIONS_OFFICE_LIST_GENERAL_FORM = "30828";
    public static final String ADVISING_OFFICE_LIST_GENERAL_FORM = "30829";
    public static final String ENROLLMENT_SERVICES_OFFICE_LIST_GENERAL_FORM = "45984";
    public static final String FINANCIAL_AID_OFFICE_LIST_GENERAL_FORM = "30830";
    public static final String GENERAL_OFFICE_LIST_GENERAL_FORM = "45986";
    public static final String IT_OFFICE_LIST_GENERAL_FORM = "45985";
    public static final String SRR_OFFICE_LIST_GENERAL_FORM = "30833";
    public static final String UNIVERSITY_STORE_OFFICE_LIST_GENERAL_FORM = "45987";
}
