package com.example.medicalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    ArrayList<RecMain> news = new ArrayList<RecMain>();
    ArrayList<RecMain> achivments = new ArrayList<RecMain>();
    ArrayList<RecMain> services = new ArrayList<RecMain>();

    Intent newCall;
    Intent serviceCall;

    String id;

    RecyclerView rvServ;
    RecyclerView rvAch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = getIntent().getStringExtra("id");

        newCall = new Intent(this, NewsPaper.class);
        serviceCall = new Intent(this, ServiseChoose.class);

        rvAch = findViewById(R.id.rvAchivments);
        rvServ = findViewById(R.id.rvService);

        rvNews();
        rvAchivments();
        rvServices();

        checkLocationPermission();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Че там как там")
                        .setMessage("Эу")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Main.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public void toProfile(View view){
        Intent profile = new Intent(this, Profile.class);
        profile.putExtra("id", id);
        startActivity(profile);
    }

    public void rvNews(){

        news.addAll(createNewsList());
        RecyclerView rvNews = findViewById(R.id.rvNews);

        RecNewsAdapter.OnNewClickListener newsClickListener = new RecNewsAdapter.OnNewClickListener() {
            @Override
            public void onNewsClick(RecMain news, int position) {
                newCall.putExtra("title",news.getTitle());
                newCall.putExtra("desc",news.getDescription());
                newCall.putExtra("fulldesc",news.getFullDescription());
                newCall.putExtra("img",news.getImg());
                startActivity(newCall);
            }
        };

        RecNewsAdapter adapter = new RecNewsAdapter(this, news, newsClickListener);
        rvNews.setAdapter(adapter);
        rvNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void rvAchivments(){

        achivments.addAll(createAchivmentsList());

        RecInfoAdapter.OnInfoClickListener infoClickListener = new RecInfoAdapter.OnInfoClickListener() {
            @Override
            public void onInfoClick(RecMain infos, int position) {
                Toast.makeText(getApplicationContext(), infos.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        RecInfoAdapter adapter = new RecInfoAdapter(this, achivments, infoClickListener);
        rvAch.setAdapter(adapter);
        rvAch.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void rvServices(){
        services.addAll(createServicesList());

        RecServiceAdapter.OnServiceClickListener serviceClickListener = new RecServiceAdapter.OnServiceClickListener() {
            @Override
            public void onServiceClick(RecMain services, int position) {
                serviceCall.putExtra("title",services.getTitle());
                serviceCall.putExtra("desc",services.getDescription());
                serviceCall.putExtra("img",services.getImg());
                serviceCall.putExtra("id", id);
                startActivity(serviceCall);
            }
        };

        RecServiceAdapter adapter = new RecServiceAdapter(this, services, serviceClickListener);
        rvServ.setAdapter(adapter);
        rvServ.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public ArrayList<RecMain> createNewsList() {

        ArrayList<RecMain> news = new ArrayList<>();
        news.add(new RecMain("Мы открылись!", "Представляете?", R.drawable.news_clinique,
                "Открытие нашего нового комплекса вы можете сами оценить по адресу " +
                        "Загородный проспект, 8, Санкт-Петербург. В ассортименте наших услуг вы " +
                        "можете заказать анализ крови, тест на сифилис, тест на беременность и " +
                        "коронная процедура - тест на ковид"));
        news.add(new RecMain("Вакцинация.", "*Без QR:(", R.drawable.news_vac,
                "Вакцинация без получения QR-кода происходит в частном порядке. " +
                        "Отсутствие кода обусловлено отменой федеральных ограничейний. Вакцинация осуществляется " +
                        "исключительно для личного спокойствия"));
        news.add(new RecMain("Наши врачи", "Устроили забастовку.", R.drawable.news_doctor,
                "Врачи наших клиник были немного недовольны своей зарплатой. " +
                        "Каждый опрошенный врач считает, что получает слишком большую сумму. " +
                        "Внутренний конфликт улажен, зарплаты докторам урезали."));
        news.add(new RecMain("Новые филиалы", "В странах европы.", R.drawable.news_europe,
                "Новые филиалы открыли в следующих странах: " +
                        "Эстония, Латвия, Литва, Польша, германия, Италия и Испания."));

        return news;
    }

    public ArrayList<RecMain> createServicesList() {

        ArrayList<RecMain> achivments = new ArrayList<>();
        achivments.add(new RecMain("Анализ крови", "общий", R.drawable.service_blood, ""));
        achivments.add(new RecMain("Силифилс", "анализ", R.drawable.service_syph, ""));
        achivments.add(new RecMain("Коронавирус", "ПЦР-тест", R.drawable.service_covid, ""));
        achivments.add(new RecMain("Беременность", "быстрый тест", R.drawable.service_pregnant, ""));

        return achivments;
    }

    public ArrayList<RecMain> createAchivmentsList() {

        ArrayList<RecMain> services = new ArrayList<>();
        services.add(new RecMain("10 лет", "на рынке", R.drawable.achivments_time, ""));
        services.add(new RecMain("Рейтинг", "выше аналогов", R.drawable.achivments_3stars, ""));
        services.add(new RecMain("Доверие", "10 000 клиентов", R.drawable.achivments_trust, ""));
        services.add(new RecMain("Гарантия", "лучшего качества", R.drawable.achivments_quality, ""));

        return services;
    }
}