package com.nirmalya.governmentexams.adapter;

public class CandidateListModel {

    String candidateID,rollNoumber,examDate,examStartTime,examEndTime,examCenter,examShift,candidateMobileNo,entryStatus,hallStatus,barcode;

    public CandidateListModel(String candidateID, String rollNoumber, String examDate, String examStartTime, String examEndTime, String examCenter, String examShift, String candidateMobileNo, String entryStatus, String hallStatus, String barcode) {
        this.candidateID = candidateID;
        this.rollNoumber = rollNoumber;
        this.examDate = examDate;
        this.examStartTime = examStartTime;
        this.examEndTime = examEndTime;
        this.examCenter = examCenter;
        this.examShift = examShift;
        this.candidateMobileNo = candidateMobileNo;
        this.entryStatus = entryStatus;
        this.hallStatus = hallStatus;
        this.barcode = barcode;
    }

    public String getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(String candidateID) {
        this.candidateID = candidateID;
    }

    public String getRollNoumber() {
        return rollNoumber;
    }

    public void setRollNoumber(String rollNoumber) {
        this.rollNoumber = rollNoumber;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(String examStartTime) {
        this.examStartTime = examStartTime;
    }

    public String getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(String examEndTime) {
        this.examEndTime = examEndTime;
    }

    public String getExamCenter() {
        return examCenter;
    }

    public void setExamCenter(String examCenter) {
        this.examCenter = examCenter;
    }

    public String getExamShift() {
        return examShift;
    }

    public void setExamShift(String examShift) {
        this.examShift = examShift;
    }

    public String getCandidateMobileNo() {
        return candidateMobileNo;
    }

    public void setCandidateMobileNo(String candidateMobileNo) {
        this.candidateMobileNo = candidateMobileNo;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
