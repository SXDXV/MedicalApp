package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ServiseChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servise_choose);

        ImageView image = findViewById(R.id.img);
        TextView TXTtitle = findViewById(R.id.TVtitle);
        TextView TXTdesc = findViewById(R.id.TVdescription);

        Intent fromMain = getIntent();

        int img = fromMain.getIntExtra("img", 0);
        String title = fromMain.getStringExtra("title");
        String desc = fromMain.getStringExtra("desc");

        image.setImageResource(img);
        TXTtitle.setText(title);
        TXTdesc.setText(desc);
    }

    public void toMain(View view){
        Intent main = new Intent(this, Main.class);
        startActivity(main);
    }

    public void toDoctor(View view){
        TextView title = findViewById(R.id.TVtitle);
        Intent doc = new Intent(this, Doctor.class);
        doc.putExtra("title",title.getText());;
        startActivity(doc);
    }

    public void toHome(View view){
        TextView title = findViewById(R.id.TVtitle);
        Intent docHome = new Intent(this, DoctorHome.class);
        docHome.putExtra("title",title.getText());;
        startActivity(docHome);
    }
}