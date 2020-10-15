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

    public ScanDataRequest(String scanType, List<CandidateRequestData> candidates) {
        this.scanType = scanType;
        this.candidates = candidates;
    }
}
