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

public class RecNewsAdapter extends RecyclerView.Adapter<RecNewsAdapter.ViewHolder> {

    private final OnNewClickListener onClickListener;
    private final List<RecMain> mNews;
    private final LayoutInflater inflater;

    interface OnNewClickListener {
        void onNewsClick(RecMain news, int position);
    }

    RecNewsAdapter(Context context, List<RecMain> mNews, OnNewClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.mNews = mNews;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecNewsAdapter.ViewHolder holder, int position) {
        RecMain news = mNews.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вызов метода слушателя, передавая ему данные
                onClickListener.onNewsClick(news, position);
            }
        });

        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
        holder.img.setImageResource(news.getImg());
    }

    @Override
    public int getItemCount() {
        return mNews.size();
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