package com.nirmalya.irms.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScanDataRequest {

    @SerializedName("ScanType")
    @Expose
    private String scanType;
    @SerializedName("Candidates")
    @Expose
    private List<CandidateRequestData> candidates = null;

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public List<CandidateRequestData> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateRequestData> candidates) {
        this.candidates = candidates;
    }
}
