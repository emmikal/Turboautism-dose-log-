package com.example.turboautismdoselog;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DrugDao {

    @Insert
    void insert(DrugEntry entry);

    @Query("SELECT * FROM DrugEntry ORDER BY timestamp DESC")
    List<DrugEntry> getAll();
}