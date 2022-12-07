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

public class RecInfoAdapter extends RecyclerView.Adapter<RecInfoAdapter.ViewHolder> {

    private final OnInfoClickListener onClickListener;
    private final List<RecMain> mInfo;
    private final LayoutInflater inflater;

    interface OnInfoClickListener {
        void onInfoClick(RecMain infos, int position);
    }

    RecInfoAdapter(Context context, List<RecMain> mInfo, OnInfoClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.mInfo = mInfo;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecInfoAdapter.ViewHolder holder, int position) {
        RecMain infos = mInfo.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызов метода слушателя, передавая ему данные
                onClickListener.onInfoClick(infos, position);
            }
        });

        holder.title.setText(infos.getTitle());
        holder.description.setText(infos.getDescription());
        holder.img.setImageResource(infos.getImg());
    }

    @Override
    public int getItemCount() {
        return mInfo.size();
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
