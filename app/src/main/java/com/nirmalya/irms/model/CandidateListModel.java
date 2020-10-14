package com.nirmalya.irms.model;

public class CandidateListModel {

    Integer serialNo;
    String rollNoumber, barcode, entryStatus, entryScanTime, hallStatus, hallScanTime;

    public CandidateListModel(Integer serialNo, String rollNoumber, String barcode, String entryStatus, String entryScanTime, String hallStatus, String hallScanTime) {
        this.serialNo = serialNo;
        this.rollNoumber = rollNoumber;
        this.barcode = barcode;
        this.entryStatus = entryStatus;
        this.entryScanTime = entryScanTime;
        this.hallStatus = hallStatus;
        this.hallScanTime = hallScanTime;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getRollNoumber() {
        return rollNoumber;
    }

    public void setRollNoumber(String rollNoumber) {
        this.rollNoumber = rollNoumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getEntryScanTime() {
        return entryScanTime;
    }

    public void setEntryScanTime(String entryScanTime) {
        this.entryScanTime = entryScanTime;
    }

    public String getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(String hallStatus) {
        this.hallStatus = hallStatus;
    }

    public String getHallScanTime() {
        return hallScanTime;
    }

    public void setHallScanTime(String hallScanTime) {
        this.hallScanTime = hallScanTime;
    }
}
