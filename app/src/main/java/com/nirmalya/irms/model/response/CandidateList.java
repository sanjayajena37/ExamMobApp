package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CandidateList {

    @SerializedName("RollNumber")
    @Expose
    private String rollNumber;

    @SerializedName("GenderCode")
    @Expose
    private String genderCode;

    @SerializedName("BarCode")
    @Expose
    private String barCode;

    @SerializedName("DateOfAllocation")
    @Expose
    private String dateOfAllocation;

    @SerializedName("TimeOfAllocation")
    @Expose
    private String timeOfAllocation;

    @SerializedName("ApplicationCode")
    @Expose
    private String applicationCode;

    @SerializedName("RegistrationCode")
    @Expose
    private String registrationCode;

    @SerializedName("HallNumber")
    @Expose
    private String hallNumber;

    @SerializedName("ExamDate")
    @Expose
    private String examDate;

    @SerializedName("SubjectCode")
    @Expose
    private String subjectCode;

    @SerializedName("TestCode")
    @Expose
    private String testCode;

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDateOfAllocation() {
        return dateOfAllocation;
    }

    public void setDateOfAllocation(String dateOfAllocation) {
        this.dateOfAllocation = dateOfAllocation;
    }

    public String getTimeOfAllocation() {
        return timeOfAllocation;
    }

    public void setTimeOfAllocation(String timeOfAllocation) {
        this.timeOfAllocation = timeOfAllocation;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
}
