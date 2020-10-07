package com.nirmalya.governmentexams.network;

import com.nirmalya.governmentexams.model.responce.StudentResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    String SUB_URL = "api/json/";

    @GET(SUB_URL + "get/bVLiuVTyJK?indent=2")
    Call<StudentResponse> getStudentList();
}