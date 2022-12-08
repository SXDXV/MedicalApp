package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SignUp extends AppCompatActivity {

    final String TAG = "logfire";
    Intent signIn;

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    String userID;
    TextInputLayout email;
    TextInputLayout name;
    TextInputLayout date;
    TextInputLayout pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MedicalApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signIn = new Intent(this, SignIn.class);
    }

    public void create (View view){
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.textEmailup);
        name = findViewById(R.id.textName);
        date = findViewById(R.id.textDate);
        pass = findViewById(R.id.textPasswordup);

        registration(String.valueOf(email.getEditText().getText()),String.valueOf(pass.getEditText().getText()));
    }

    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();

                            shareToBase();
                            startActivity(signIn);
                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast toast = Toast.makeText(getApplicationContext(),
                                task.getException().toString(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
    }

    public void shareToBase(){
//        try {
            database = FirebaseDatabase.getInstance().getReference("Users");

            Person demoPerson = new Person(
                    userID,
                    String.valueOf(name.getEditText().getText()),
                    "town",
                    String.valueOf(date.getEditText().getText()),
                    String.valueOf(email.getEditText().getText()),
                    "phone",
                    "snils",
                    "passport",
                    String.valueOf(pass.getEditText().getText())
            );
//            user.put("name",String.valueOf(name.getEditText().getText()));
//            user.put("town",null);
//            user.put("date",String.valueOf(date.getEditText().getText()));
//            user.put("email",String.valueOf(email.getEditText().getText()));
//            user.put("phone",null);
//            user.put("snils",null);
//            user.put("passport",null);
//            user.put("pass",String.valueOf(pass.getEditText().getText()));

            //myRef.setValue(user);
            //database.child("users").setValue(demoPerson);
            database.child(String.valueOf(demoPerson.getId())).setValue(demoPerson);
//        }catch (Exception exception){
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    exception.toString(), Toast.LENGTH_SHORT);
//            toast.show();
//            Log.w(TAG, "createUserWithEmail:failure", exception);
//        }
    }

    public void toAuth (View view){
        startActivity(signIn);
    }
}