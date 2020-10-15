package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateAttendanceResponse {

    @SerializedName("TotalCandidate")
    @Expose
    private Integer totalCandidate;

    @SerializedName("CandidateList")
    @Expose
    private List<CandidateAttendanceList> candidateList = null;

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
    private String data;

    public Integer getTotalCandidate() {
        return totalCandidate;
    }

    public void setTotalCandidate(Integer totalCandidate) {
        this.totalCandidate = totalCandidate;
    }

    public List<CandidateAttendanceList> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<CandidateAttendanceList> candidateList) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
