package com.example.groupproject.service;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;
import com.example.groupproject.adapter.SpacingBetweenAppointment;
import com.example.groupproject.adapter.TemplateAdapter;
import com.example.groupproject.bookappointment.BookAppointment_Main;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Consultation extends AppCompatActivity {
    private ImageButton general;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_consultation);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        general = findViewById(R.id.general);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consultation.this, BookAppointment_Main.class);
                intent.putExtra("specialty", "General");
                intent.putExtra("patientUserId", userid);
                startActivity(intent);
            }
        });


    }
}
