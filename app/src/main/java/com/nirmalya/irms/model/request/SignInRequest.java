package com.nirmalya.irms.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInRequest {

    @SerializedName("DeviceId")
    @Expose
    private String deviceID;

    @SerializedName("Pin")
    @Expose
    private String pin;

    public SignInRequest(String deviceID, String pin) {
        this.deviceID = deviceID;
        this.pin = pin;
    }
}
