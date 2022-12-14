package com.example.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Doctor extends AppCompatActivity {

    TextView TXTtitle;
    Spinner TXTadress;
    CalendarView TXTdate;
    String id;
    String title;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Intent fromServiceChoose = getIntent();

        title = fromServiceChoose.getStringExtra("title");
        id = fromServiceChoose.getStringExtra("id");
        TXTtitle = findViewById(R.id.TVtitle);
        TXTadress = findViewById(R.id.spinner);
        TXTdate = findViewById(R.id.calendarView);
        TXTtitle.setText(title);
    }

    public void entry(View view){
        String adress = TXTadress.getSelectedItem().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(TXTdate.getDate());

        database = FirebaseDatabase.getInstance().getReference("Appointment");

        Map<String, Object> appointMap = new HashMap<>();
        appointMap.put("id", id);
        appointMap.put("service", title);
        appointMap.put("adress", adress);
        appointMap.put("date", date);

        database.child(id).setValue(appointMap);


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