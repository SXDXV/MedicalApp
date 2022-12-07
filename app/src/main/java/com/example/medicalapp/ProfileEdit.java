package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
    }

    public void toProfile(View view){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }

    public void saveInfo(View view){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }
}