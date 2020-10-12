package com.nirmalya.irms.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateOTPRequest {

    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("DeviceId")
    @Expose
    private String deviceID;

    @SerializedName("OTP")
    @Expose
    private String OTP;

    public ValidateOTPRequest(String mobileNumber, String deviceID, String OTP) {
        this.mobileNumber = mobileNumber;
        this.deviceID = deviceID;
        this.OTP = OTP;
    }
}
