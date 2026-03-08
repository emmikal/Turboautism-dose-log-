package com.example.turboautismdoselog;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {

    TextView statTotalEntries;
    TextView statMostUsedDrug;
    TextView statLastDose;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        statTotalEntries = findViewById(R.id.statTotalEntries);
        statMostUsedDrug = findViewById(R.id.statMostUsedDrug);
        statLastDose = findViewById(R.id.statLastDose);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "drug_database"
        ).allowMainThreadQueries().build();

        loadStatistics();
    }

    private void loadStatistics() {

        List<DrugEntry> entries = db.drugDao().getAll();

        statTotalEntries.setText("Total entries: " + entries.size());

        HashMap<String, Integer> counts = new HashMap<>();

        for (DrugEntry entry : entries) {

            if (!counts.containsKey(entry.drug)) {
                counts.put(entry.drug, 1);
            } else {
                counts.put(entry.drug, counts.get(entry.drug) + 1);
            }
        }

        String mostUsed = "None";
        int max = 0;

        for (String drug : counts.keySet()) {
            if (counts.get(drug) > max) {
                max = counts.get(drug);
                mostUsed = drug;
            }
        }

        statMostUsedDrug.setText("Most used drug: " + mostUsed);

        if (!entries.isEmpty()) {

            DrugEntry last = entries.get(entries.size() - 1);

            Date date = new Date(last.timestamp);
            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

            statLastDose.setText("Last dose: " + sdf.format(date));
        }
    }
}