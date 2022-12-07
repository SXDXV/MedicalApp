package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Intent fromServiceChoose = getIntent();
        TextView TXTtitle = findViewById(R.id.TVtitle);
        String title = fromServiceChoose.getStringExtra("title");
        TXTtitle.setText(title);
    }

    public void entry(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Вы успешно записаны. Вам позвонят позже для согласования времени.", Toast.LENGTH_LONG);
        toast.show();

        Intent main = new Intent(this, Main.class);
        startActivity(main);
    }

    public void toServiceChoose(View view){
        Intent serChoose = new Intent(this, ServiseChoose.class);
        startActivity(serChoose);
    }
}