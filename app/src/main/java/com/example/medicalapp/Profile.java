package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void toMain(View view){
        Intent main = new Intent(this, Main.class);
        startActivity(main);
    }

    public void changeInfo(View view){
        Intent edit = new Intent(this, ProfileEdit.class);
        startActivity(edit);
    }
}