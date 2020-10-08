package com.nirmalya.irms.network;

import com.nirmalya.irms.model.responce.StudentResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String SUB_URL = "api/json/";

    @GET(SUB_URL + "get/bVLiuVTyJK?indent=2")
    Call<StudentResponse> getStudentList();
}