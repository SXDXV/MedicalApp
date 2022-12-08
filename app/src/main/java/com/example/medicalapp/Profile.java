package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView TVname;
    TextView TVtown;
    TextView TVdate;
    TextView TVemail;
    TextView TVphone;
    TextView TVsnils;
    TextView TVpassport;

    String id;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id = getIntent().getStringExtra("id");
        base(id);
    }

    public void initialize(){
        TVname = findViewById(R.id.textViewName);
        TVtown = findViewById(R.id.textViewTown);
        TVdate = findViewById(R.id.textViewDate);
        TVemail = findViewById(R.id.textViewEmail);
        TVphone = findViewById(R.id.textViewPhone);
        TVsnils = findViewById(R.id.textViewSnils);
        TVpassport = findViewById(R.id.textViewPassport);
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

    public void toMain(View view){
        Intent main = new Intent(this, Main.class);
        startActivity(main);
    }

    public void changeInfo(View view){
        Intent edit = new Intent(this, ProfileEdit.class);
        edit.putExtra("id", id);
        startActivity(edit);
    }
}