package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateResponse {

    @SerializedName("TotalCandidate")
    @Expose
    private Integer totalCandidate;

    @SerializedName("Exam_Date")
    @Expose
    private String examDate;

    @SerializedName("Exam_Shift")
    @Expose
    private String examShift;

    @SerializedName("CandidateList")
    @Expose
    private List<CandidateList> candidateList = null;

    @SerializedName("Success")
    @Expose
    private Boolean success;

    @SerializedName("Code")
    @Expose
    private Integer code;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("Data")
    @Expose
    private Object data;

    public Integer getTotalCandidate() {
        return totalCandidate;
    }

    public void setTotalCandidate(Integer totalCandidate) {
        this.totalCandidate = totalCandidate;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamShift() {
        return examShift;
    }

    public void setExamShift(String examShift) {
        this.examShift = examShift;
    }

    public List<CandidateList> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<CandidateList> candidateList) {
        this.candidateList = candidateList;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
