package com.nirmalya.irms.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentList {

    @SerializedName("studentID")
    @Expose
    private Integer studentID;

    @SerializedName("studentMobileNo")
    @Expose
    private Integer studentMobileNo;

    @SerializedName("examShift")
    @Expose
    private String examShift;

    @SerializedName("studentName")
    @Expose
    private String studentName;

    @SerializedName("studentImage")
    @Expose
    private String studentImage;

    @SerializedName("examDate")
    @Expose
    private String examDate;

    @SerializedName("examTime")
    @Expose
    private String examTime;

    @SerializedName("examCenter")
    @Expose
    private String examCenter;

    @SerializedName("studentRollNo")
    @Expose
    private Integer studentRollNo;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getStudentMobileNo() {
        return studentMobileNo;
    }

    public void setStudentMobileNo(Integer studentMobileNo) {
        this.studentMobileNo = studentMobileNo;
    }

    public String getExamShift() {
        return examShift;
    }

    public void setExamShift(String examShift) {
        this.examShift = examShift;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamCenter() {
        return examCenter;
    }

    public void setExamCenter(String examCenter) {
        this.examCenter = examCenter;
    }

    public Integer getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(Integer studentRollNo) {
        this.studentRollNo = studentRollNo;
    }
}
