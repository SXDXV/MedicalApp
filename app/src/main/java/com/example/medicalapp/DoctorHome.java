package com.example.medicalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DoctorHome extends AppCompatActivity {

    TextView TXTtitle;
    CalendarView TXTcalendar;
    TextView TXTdate;
    TextView TXTadres;
    String id;
    String title;
    String adress;
    Person person;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Intent fromServiceChoose = getIntent();
        TXTtitle = findViewById(R.id.TVtitle);
        id = fromServiceChoose.getStringExtra("id");
        title = fromServiceChoose.getStringExtra("title");
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
        TXTadres = findViewById(R.id.TVadress);
        TXTtitle.setText(title);
        base(id);
    }

    public void base(String id){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                person = snapshot.child(id).getValue(Person.class);
                adress = person.getTown();
                TXTadres.setText("Ваш адрес: " + adress);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void entry(View view){
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