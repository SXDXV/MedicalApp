package com.example.medicalapp;

import androidx.annotation.NonNull;
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

import java.util.HashMap;
import java.util.Map;

public class Doctor extends AppCompatActivity {

    TextView TXTtitle;
    Spinner TXTadress;
    CalendarView TXTcalendar;
    TextView TXTdate;
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
        TXTcalendar = findViewById(R.id.calendarView);
        TXTcalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                int bYear = year;
                int bMonth = month + 1;
                int bDay = day;

                String date2 = Integer.toString(bDay) + "/" + Integer.toString(bMonth) + "/" + Integer.toString(bYear);
                TXTdate = findViewById(R.id.TVdate2);
                TXTdate.setText(date2);
            }
        });
        TXTtitle.setText(title);
    }

    public void entry(View view){
        String adress = TXTadress.getSelectedItem().toString();

        TXTdate = findViewById(R.id.TVdate2);
        String date = TXTdate.getText().toString();

        database = FirebaseDatabase.getInstance().getReference("Appointment").child(id);

        Map<String, Object> appointMap = new HashMap<>();
        appointMap.put("id", id);
        appointMap.put("service", title);
        appointMap.put("adress", adress);
        appointMap.put("date", date);

        database.child(title).setValue(appointMap);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Вы успешно записаны. Вам позвонят позже для согласования времени.", Toast.LENGTH_LONG);
        toast.show();

        Intent main = new Intent(this, Main.class);
        main.putExtra("id", id);
        startActivity(main);
    }

    public void toServiceChoose(View view){
        Intent serChoose = new Intent(this, ServiseChoose.class);
        serChoose.putExtra("id", id);
        startActivity(serChoose);
    }
}