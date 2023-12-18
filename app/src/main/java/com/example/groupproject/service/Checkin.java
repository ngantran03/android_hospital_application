package com.example.groupproject.service;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Checkin extends AppCompatActivity {
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_checkin);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");


    }
}
