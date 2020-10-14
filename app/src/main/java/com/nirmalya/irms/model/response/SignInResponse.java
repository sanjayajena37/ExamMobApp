package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    @SerializedName("Success")
    private Boolean success;

    @SerializedName("Code")
    @Expose
    private Integer code;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private ScannerData scannerData;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ScannerData getScannerData() {
        return scannerData;
    }

    public void setScannerData(ScannerData scannerData) {
        this.scannerData = scannerData;
    }
}
