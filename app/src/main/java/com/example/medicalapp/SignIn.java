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

public class SignIn extends AppCompatActivity {

    Intent signUp;
    Intent main;
    final String TAG = "logfire";

    private FirebaseAuth mAuth;
    TextInputLayout email;
    TextInputLayout pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MedicalApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        signUp = new Intent(this, SignUp.class);
        main = new Intent(this, Main.class);
    }

    public void toMain(View view){
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.textEmailin);
        pass = findViewById(R.id.textPasswordin);

        sign(String.valueOf(email.getEditText().getText()),String.valueOf(pass.getEditText().getText()));

        startActivity(main);
    }

    public void sign(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public void toRegistration(View view){
        startActivity(signUp);
    }
}