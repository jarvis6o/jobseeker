package com.example.jobseeker;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JobDao {

    @Query("SELECT * FROM job WHERE id = :id")
    LiveData<Job> get(int id);

    @Query("SELECT * FROM job ORDER BY id ASC")
    LiveData<List<Job>> getAll();

    @Query("SELECT * FROM job WHERE id IN (:jobIds)")
    LiveData<List<Job>> getByIds(int[] jobIds);

    @Query("SELECT * FROM job WHERE employer LIKE :employer AND " +
            "title LIKE :title LIMIT 1")
    LiveData<Job> findByName(String employer, String title);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAll(Job... jobs);

    @Update
    void updateAll(Job... jobs);

    @Delete
    void delete(Job job);
}
