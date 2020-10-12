package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScannerData {

    @SerializedName("ScannerId")
    @Expose
    private Integer scannerId;

    @SerializedName("ScannerMobileNo")
    @Expose
    private String scannerMobileNo;

    @SerializedName("distCode")
    @Expose
    private String distCode;

    @SerializedName("CenterCode")
    @Expose
    private String centerCode;

    @SerializedName("AssessToken")
    @Expose
    private String assessToken;

    public Integer getScannerId() {
        return scannerId;
    }

    public void setScannerId(Integer scannerId) {
        this.scannerId = scannerId;
    }

    public String getScannerMobileNo() {
        return scannerMobileNo;
    }

    public void setScannerMobileNo(String scannerMobileNo) {
        this.scannerMobileNo = scannerMobileNo;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getAssessToken() {
        return assessToken;
    }

    public void setAssessToken(String assessToken) {
        this.assessToken = assessToken;
    }
}
