package com.nirmalya.governmentexams.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentResponse {

    @SerializedName("studentList")
    @Expose
    private List<StudentList> studentList = null;

    public List<StudentList> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentList> studentList) {
        this.studentList = studentList;
    }
}
