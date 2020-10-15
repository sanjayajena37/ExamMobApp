package com.nirmalya.irms.RoomDB;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class StudentModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    long id;

    String stRollNo;
    String stBarcode;
    String entryStatus;
    String entryScanTime;
    String hallStatus;
    String hallScanTime;

    public StudentModel(String stRollNo, String stBarcode, String entryStatus, String entryScanTime, String hallStatus, String hallScanTime) {
        this.id = id;
        this.stRollNo = stRollNo;
        this.stBarcode = stBarcode;
        this.entryStatus = entryStatus;
        this.entryScanTime = entryScanTime;
        this.hallStatus = hallStatus;
        this.hallScanTime = hallScanTime;
    }


    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", stRollNo='" + stRollNo + '\'' +
                ", stBarcode='" + stBarcode + '\'' +
                ", entryStatus=" + entryStatus +
                ", entryScanTime=" + entryScanTime +
                ", hallStatus=" + hallStatus +
                ", hallScanTime=" + hallScanTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getEntryScanTime() {
        return entryScanTime;
    }

    public void setEntryScanTime(String entryScanTime) {
        this.entryScanTime = entryScanTime;
    }

    public String getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(String hallStatus) {
        this.hallStatus = hallStatus;
    }

    public String getHallScanTime() {
        return hallScanTime;
    }

    public void setHallScanTime(String hallScanTime) {
        this.hallScanTime = hallScanTime;
    }


    public String theString() {
        return "StudentModel{" +
                "id=" + id +
                ", stRollNo='" + stRollNo + '\'' +
                ", stBarcode='" + stBarcode + '\'' +
                ", entryStatus='" + entryStatus + '\'' +
                ", entryScanTime='" + entryScanTime + '\'' +
                ", hallStatus='" + hallStatus + '\'' +
                ", hallScanTime='" + hallScanTime + '\'' +
                '}';
    }
}
