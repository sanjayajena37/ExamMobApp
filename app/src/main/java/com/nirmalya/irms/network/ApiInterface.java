package com.nirmalya.irms.network;

import com.nirmalya.irms.model.request.SetPinRequest;
import com.nirmalya.irms.model.request.SignInRequest;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.model.request.ValidateOTPRequest;
import com.nirmalya.irms.model.response.CommonResponse;
import com.nirmalya.irms.model.response.SignInResponse;
import com.nirmalya.irms.model.response.StudentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    String SUB_URL = "api/UserLogin/";

    @POST(SUB_URL + "SignUp")
    Call<CommonResponse> postSendOTP(@Body SignupSendMobileRequest signupSendMobileRequest);

    @POST(SUB_URL + "OTPVerification")
    Call<CommonResponse> validateOTP(@Body ValidateOTPRequest validateOTPRequest);

    @POST(SUB_URL + "ResentOTP")
    Call<CommonResponse> reSendOTP(@Body SignupSendMobileRequest signupSendMobileRequest);

    @POST(SUB_URL + "SetUpPin")
    Call<CommonResponse> setUpPin(@Body SetPinRequest setPinRequest);

    @POST(SUB_URL + "ForgotPin")
    Call<CommonResponse> forgotPin(@Body SignupSendMobileRequest signupSendMobileRequest);

    @POST(SUB_URL + "SignIn")
    Call<SignInResponse> login(@Body SignInRequest signInRequest);
}