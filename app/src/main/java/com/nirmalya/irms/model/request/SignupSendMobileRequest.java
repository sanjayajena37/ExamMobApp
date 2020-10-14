package com.nirmalya.irms.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupSendMobileRequest {

    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("DeviceId")
    @Expose
    private String deviceID;

    public SignupSendMobileRequest(String mobileNumber, String deviceID) {
        this.mobileNumber = mobileNumber;
        this.deviceID = deviceID;
    }
}
