package com.nirmalya.irms.RoomDB;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM students")
    List<StudentModel> allResorces();


    @Query("DELETE FROM students")
    void deleteAll();

    @Insert
    void insertResource(StudentModel student);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateResource(StudentModel model);

    @Query("DELETE FROM students WHERE id = :id")
    void deleteResource(long id);

    @Query("SELECT * FROM students WHERE stBarcode = :barcode")
    StudentModel selectData(String barcode);

    @Query("SELECT COUNT(*) FROM students")
    long getCount();

}
