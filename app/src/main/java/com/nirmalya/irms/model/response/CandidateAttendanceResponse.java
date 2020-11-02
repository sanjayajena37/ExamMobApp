package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateAttendanceResponse {

    @SerializedName("TotalCandidate")
    @Expose
    private Integer totalCandidate;

    @SerializedName("TotalCandidateGateList")
    @Expose
    private Integer totalCandidateGateList;

    @SerializedName("TotalCandidateHallList")
    @Expose
    private Integer totalCandidateHallList;

    @SerializedName("CentreName")
    @Expose
    private String centreName;

    @SerializedName("DistrictName")
    @Expose
    private String districtName;

    @SerializedName("CandidateList")
    @Expose
    private List<CandidateAttendanceList> candidateList = null;

    @SerializedName("TotalCandidateEntryScanList")
    @Expose
    private List<CandidateAttendanceList> candidateEntryList = null;

    @SerializedName("TotalCandidateAttendanceHall")
    @Expose
    private List<CandidateAttendanceList> candidateHallList = null;

    @SerializedName("HallAttendScannerwiseList")
    @Expose
    private List<CandidateAttendanceList> scannerScanHallList = null;

    @SerializedName("GateAttendScannerwiseList")
    @Expose
    private List<CandidateAttendanceList> scannerScanGateList = null;

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

    public Integer getTotalCandidateGateList() {
        return totalCandidateGateList;
    }

    public void setTotalCandidateGateList(Integer totalCandidateGateList) {
        this.totalCandidateGateList = totalCandidateGateList;
    }

    public Integer getTotalCandidateHallList() {
        return totalCandidateHallList;
    }

    public void setTotalCandidateHallList(Integer totalCandidateHallList) {
        this.totalCandidateHallList = totalCandidateHallList;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<CandidateAttendanceList> getCandidateEntryList() {
        return candidateEntryList;
    }

    public void setCandidateEntryList(List<CandidateAttendanceList> candidateEntryList) {
        this.candidateEntryList = candidateEntryList;
    }

    public List<CandidateAttendanceList> getCandidateHallList() {
        return candidateHallList;
    }

    public void setCandidateHallList(List<CandidateAttendanceList> candidateHallList) {
        this.candidateHallList = candidateHallList;
    }

    public List<CandidateAttendanceList> getScannerScanHallList() {
        return scannerScanHallList;
    }

    public void setScannerScanHallList(List<CandidateAttendanceList> scannerScanHallList) {
        this.scannerScanHallList = scannerScanHallList;
    }

    public List<CandidateAttendanceList> getScannerScanGateList() {
        return scannerScanGateList;
    }

    public void setScannerScanGateList(List<CandidateAttendanceList> scannerScanGateList) {
        this.scannerScanGateList = scannerScanGateList;
    }
}
