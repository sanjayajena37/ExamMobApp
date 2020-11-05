package com.nirmalya.irms.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nirmalya.irms.model.DistList;

import java.util.List;

public class DistResponse {

    @SerializedName("Success")
    @Expose
    private Boolean success;

    @SerializedName("Code")
    @Expose
    private Integer code;

    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("distList")
    @Expose
    private List<DistList> distList = null;

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

    public List<DistList> getDistList() {
        return distList;
    }

    public void setDistList(List<DistList> distList) {
        this.distList = distList;
    }
}
