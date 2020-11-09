package com.nirmalya.irms.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistList {

    @SerializedName("DistName")
    @Expose
    private String distName;

    @SerializedName("DistId")
    @Expose
    private String distID;

    public DistList() {
    }

    public DistList(String distName, String distID) {
        this.distName = distName;
        this.distID = distID;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getDistID() {
        return distID;
    }

    public void setDistID(String distID) {
        this.distID = distID;
    }
}
