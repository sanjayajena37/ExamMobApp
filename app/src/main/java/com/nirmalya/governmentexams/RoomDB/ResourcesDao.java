package com.nirmalya.governmentexams.RoomDB;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ResourcesDao {

    @Query("SELECT * FROM resources")
    List<Resource> allResorces();

    @Query("SELECT * FROM resources WHERE extension='html'")
    List<Resource> allHtmlResorces();

    @Query("DELETE FROM resources")
    void deleteAll();

    @Insert
    void insertResource(Resource resource);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateResource(Resource resource);

    @Query("DELETE FROM resources WHERE id = :id")
    void deleteResource(long id);

    @Query("SELECT COUNT(*) FROM resources")
    long getCount();

}
