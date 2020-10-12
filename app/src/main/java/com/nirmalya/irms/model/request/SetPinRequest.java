package com.nirmalya.irms.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetPinRequest {

    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("DeviceId")
    @Expose
    private String deviceID;

    @SerializedName("Pin")
    @Expose
    private String pin;

    public SetPinRequest(String mobileNumber, String deviceID, String pin) {
        this.mobileNumber = mobileNumber;
        this.deviceID = deviceID;
        this.pin = pin;
    }
}
