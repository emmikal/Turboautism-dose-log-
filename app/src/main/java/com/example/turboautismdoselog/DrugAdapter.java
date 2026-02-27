package com.example.turboautismdoselog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> {

    private List<DrugEntry> entries;

    public DrugAdapter(List<DrugEntry> entries) {
        this.entries = entries;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDrug, textRoute, textDosage, textTimestamp;

        public ViewHolder(View view) {
            super(view);
            textDrug = view.findViewById(R.id.textDrug);
            textRoute = view.findViewById(R.id.textRoute);
            textDosage = view.findViewById(R.id.textDosage);
            textTimestamp = view.findViewById(R.id.textTimestamp);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drug_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrugEntry entry = entries.get(position);

        holder.textDrug.setText(entry.drug);
        holder.textRoute.setText("Route: " + entry.route);
        holder.textDosage.setText("Dosage: " + entry.dosage);

        Date date = new Date(entry.timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        holder.textTimestamp.setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }
}