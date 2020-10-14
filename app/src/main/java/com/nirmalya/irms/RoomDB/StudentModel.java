package com.nirmalya.irms.RoomDB;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class StudentModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    long id;

    String stExamStartTime;
    String stExamEndTime;
    String stRollNo;
    String stBarcode;
    String examShift;
    String entryStatus;
    String hallStatus;

    public StudentModel(String stExamStartTime, String stExamEndTime, String stRollNo, String stBarcode, String examShift, String entryStatus, String hallStatus) {
        this.id = id;
        this.stExamStartTime = stExamStartTime;
        this.stExamEndTime = stExamEndTime;
        this.stRollNo = stRollNo;
        this.stBarcode = stBarcode;
        this.examShift = examShift;
        this.entryStatus = entryStatus;
        this.hallStatus = hallStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStExamStartTime() {
        return stExamStartTime;
    }

    public void setStExamStartTime(String stExamStartTime) {
        this.stExamStartTime = stExamStartTime;
    }

    public String getStExamEndTime() {
        return stExamEndTime;
    }

    public void setStExamEndTime(String stExamEndTime) {
        this.stExamEndTime = stExamEndTime;
    }

    public String getStRollNo() {
        return stRollNo;
    }

    public void setStRollNo(String stRollNo) {
        this.stRollNo = stRollNo;
    }

    public String getStBarcode() {
        return stBarcode;
    }

    public void setStBarcode(String stBarcode) {
        this.stBarcode = stBarcode;
    }

    public String getExamShift() {
        return examShift;
    }

    public void setExamShift(String examShift) {
        this.examShift = examShift;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(String hallStatus) {
        this.hallStatus = hallStatus;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", stExamStartTime='" + stExamStartTime + '\'' +
                ", stExamEndTime='" + stExamEndTime + '\'' +
                ", stRollNo='" + stRollNo + '\'' +
                ", stBarcode='" + stBarcode + '\'' +
                ", examShift=" + examShift +
                ", entryStatus=" + entryStatus +
                ", hallStatus=" + hallStatus +
                '}';
    }
}
