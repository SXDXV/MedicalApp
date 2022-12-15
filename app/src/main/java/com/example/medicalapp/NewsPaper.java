package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsPaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_paper);

        ImageView image = findViewById(R.id.img);
        TextView TXTtitle = findViewById(R.id.TVtitle);
        TextView TXTdesc = findViewById(R.id.TVdescription);

        Intent fromMain = getIntent();

        int img = fromMain.getIntExtra("img", 0);
        String title = fromMain.getStringExtra("title");
        String desc = fromMain.getStringExtra("fulldesc");

        image.setImageResource(img);
        TXTtitle.setText(title);
        TXTdesc.setText(desc);
    }
}