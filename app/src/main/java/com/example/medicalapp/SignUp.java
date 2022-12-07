package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    final String TAG = "logfire";
    Intent signIn;

    private FirebaseAuth mAuth;
    TextInputLayout email;
    TextInputLayout name;
    TextInputLayout date;
    TextInputLayout pass;

    Map<String, Object> user = new HashMap<>();

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
        startActivity(signIn);
    }

    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public void toAuth (View view){
        startActivity(signIn);
    }
}