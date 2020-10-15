package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CandidateAttendanceList {

    @SerializedName("CandidateID")
    @Expose
    private Integer candidateID;

    @SerializedName("RollNumber")
    @Expose
    private String rollNumber;

    @SerializedName("EntryScanTime")
    @Expose
    private String entryScanTime;

    @SerializedName("HallScanTime")
    @Expose
    private String hallScanTime;

    @SerializedName("EntryStatus")
    @Expose
    private String entryStatus;

    @SerializedName("HallStatus")
    @Expose
    private String hallStatus;

    @SerializedName("BarCode")
    @Expose
    private String barCode;

    @SerializedName("ScannerId")
    @Expose
    private Integer scannerId;

    public Integer getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(Integer candidateID) {
        this.candidateID = candidateID;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getEntryScanTime() {
        return entryScanTime;
    }

    public void setEntryScanTime(String entryScanTime) {
        this.entryScanTime = entryScanTime;
    }

    public String getHallScanTime() {
        return hallScanTime;
    }

    public void setHallScanTime(String hallScanTime) {
        this.hallScanTime = hallScanTime;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(String hallStatus) {
        this.hallStatus = hallStatus;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getScannerId() {
        return scannerId;
    }

    public void setScannerId(Integer scannerId) {
        this.scannerId = scannerId;
    }
}
