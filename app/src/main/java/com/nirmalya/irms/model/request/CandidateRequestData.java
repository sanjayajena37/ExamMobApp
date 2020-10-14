package com.nirmalya.irms.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CandidateRequestData {

    @SerializedName("RollNumber")
    @Expose
    private String rollNumber;

    @SerializedName("BarCode")
    @Expose
    private String barCode;

    @SerializedName("EntryScanTime")
    @Expose
    private String entryScanTime;

    @SerializedName("HallScanTime")
    @Expose
    private Object hallScanTime;

    @SerializedName("EntryStatus")
    @Expose
    private String entryStatus;

    @SerializedName("HallStatus")
    @Expose
    private Object hallStatus;

    @SerializedName("ScannerId")
    @Expose
    private Integer scannerId;

    public CandidateRequestData(String rollNumber, String barCode, String entryScanTime, Object hallScanTime, String entryStatus, Object hallStatus, Integer scannerId) {
        this.rollNumber = rollNumber;
        this.barCode = barCode;
        this.entryScanTime = entryScanTime;
        this.hallScanTime = hallScanTime;
        this.entryStatus = entryStatus;
        this.hallStatus = hallStatus;
        this.scannerId = scannerId;
    }
}
