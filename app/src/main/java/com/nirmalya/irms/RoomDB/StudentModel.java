package com.nirmalya.irms.RoomDB;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class StudentModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    long id;

    String stName;
    String stImage;
    String stExamDt;
    String stExamTime;
    String stExamCenter;
    String stRollNo;
    boolean isPresent;

    public StudentModel(String stName, String stImage, String stExamDt, String stExamTime, String stExamCenter, String stRollNo, boolean isPresent) {
        this.id = id;
        this.stName = stName;
        this.stImage = stImage;
        this.stExamDt = stExamDt;
        this.stExamTime = stExamTime;
        this.stExamCenter = stExamCenter;
        this.stRollNo = stRollNo;
        this.isPresent = isPresent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStImage() {
        return stImage;
    }

    public void setStImage(String stImage) {
        this.stImage = stImage;
    }

    public String getStExamDt() {
        return stExamDt;
    }

    public void setStExamDt(String stExamDt) {
        this.stExamDt = stExamDt;
    }

    public String getStExamTime() {
        return stExamTime;
    }

    public void setStExamTime(String stExamTime) {
        this.stExamTime = stExamTime;
    }

    public String getStExamCenter() {
        return stExamCenter;
    }

    public void setStExamCenter(String stExamCenter) {
        this.stExamCenter = stExamCenter;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public String getStRollNo() {
        return stRollNo;
    }

    public void setStRollNo(String stRollNo) {
        this.stRollNo = stRollNo;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", stName='" + stName + '\'' +
                ", stImage='" + stImage + '\'' +
                ", stExamDt='" + stExamDt + '\'' +
                ", stExamTime='" + stExamTime + '\'' +
                ", stExamCenter='" + stExamCenter + '\'' +
                ", stRollNo='" + stRollNo + '\'' +
                ", isPresent=" + isPresent +
                '}';
    }
}
