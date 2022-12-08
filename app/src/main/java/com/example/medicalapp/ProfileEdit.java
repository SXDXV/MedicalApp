package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileEdit extends AppCompatActivity {

    TextView TVname;
    EditText TVtown;
    EditText TVdate;
    EditText TVemail;
    EditText TVphone;
    EditText TVsnils;
    EditText TVpassport;

    String id;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        id = getIntent().getStringExtra("id");
        base(id);
    }

    public void toProfile(View view){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }

    public void initialize(){
        TVname = findViewById(R.id.textViewNameRed);
        TVtown = findViewById(R.id.editTextTown);
        TVdate = findViewById(R.id.editTextDate);
        TVemail = findViewById(R.id.editTextEmail);
        TVphone = findViewById(R.id.editTextPhone);
        TVsnils = findViewById(R.id.editTextSnils);
        TVpassport = findViewById(R.id.editTextPassport);
        setData();
    }

    public void setData(){
        TVname.setText(person.getName());
        TVtown.setText(person.getTown());
        TVdate.setText(person.getDate());
        TVemail.setText(person.getEmail());
        TVphone.setText(person.getPhone());
        TVsnils.setText(person.getSnils());
        TVpassport.setText(person.getPassport());
    }

    public void base(String id){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                person = snapshot.child(id).getValue(Person.class);
                initialize();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveInfo(View view){
        Intent profile = new Intent(this, Main.class);
        profile.putExtra("id", id);
        startActivity(profile);
    }
}