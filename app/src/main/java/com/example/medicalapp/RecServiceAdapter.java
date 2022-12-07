package com.example.medicalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecServiceAdapter extends RecyclerView.Adapter<RecServiceAdapter.ViewHolder>{
    private final OnServiceClickListener onClickListener;
    private final List<RecMain> mService;
    private final LayoutInflater inflater;

    interface OnServiceClickListener {
        void onServiceClick(RecMain services, int position);
    }

    public RecServiceAdapter(Context context, List<RecMain> mService, OnServiceClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.mService = mService;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecMain services = mService.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызов метода слушателя, передавая ему данные
                onClickListener.onServiceClick(services, position);
            }
        });

        holder.title.setText(services.getTitle());
        holder.description.setText(services.getDescription());
        holder.img.setImageResource(services.getImg());
    }

    @Override
    public int getItemCount() {
        return mService.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TVtitle);
            description = itemView.findViewById(R.id.TVdescription);
            img = itemView.findViewById(R.id.img);
        }
    }
}
