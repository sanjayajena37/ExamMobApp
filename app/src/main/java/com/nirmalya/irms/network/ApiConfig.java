package com.nirmalya.irms.network;

/**
 * Created by User24 on 8/31/2017.
 */


/*
 * Configuration file containing all the server URLS and project configuration
 */

public class ApiConfig {

    public static String APP_KEY = "";

    public static String SRVR_URL = "https://www.osssc.gov.in/irms/";

    public static String BASE_URL = SRVR_URL + "api/Candidate/";

    //public static String STUDENT_LIST_URL = "http://www.json-generator.com/api/json/get/bVLiuVTyJK?indent=2";
    public static String STUDENT_LIST_URL = BASE_URL + "GetCandidates";
    public static String CANDIDATE_LIST_URL = BASE_URL + "GetCandidateAttendance";
}
