package com.example.turboautismdoselog;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DrugEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String drug;
    public String route;
    public String dosage;
    public long timestamp;
}