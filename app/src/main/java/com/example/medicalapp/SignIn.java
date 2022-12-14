package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.C;

import java.util.Timer;
import java.util.TimerTask;

public class SignIn extends AppCompatActivity {

//    try {
//
//    }catch (Exception exception){
//
//    }
    Intent signUp;
    Intent main;
    String userID;
    final String TAG = "logfire";

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    int indicator = 0;
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
        try {
            sign(String.valueOf(email.getEditText().getText()),String.valueOf(pass.getEditText().getText()));
        }catch (Exception exception){
            //email.setErrorTextColor(getResources().getColor(Integer.parseInt(R.color.text)));
            email.setError(getResources().getString(R.string.empty));
            pass.setError(getResources().getString(R.string.empty));

            Timer myTimer;
            myTimer = new Timer();

            myTimer.schedule(new TimerTask() {
                public void run() {
                    timerTick();
                }
            }, 0, 500);
        }
    }

    private void timerTick() {
        this.runOnUiThread(doTask);
    }

    private Runnable doTask = new Runnable() {
        public void run() {
            if (indicator == 3){
                email.setError("");
                pass.setError("");
            }
            indicator++;
        }
    };

    public void sign(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();

                            main.putExtra("id", userID);

                            startActivity(main);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Возникли проблемы со входом, проверьте правильность введенных данных", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });

    }

    public void toRegistration(View view){
        startActivity(signUp);
    }
}