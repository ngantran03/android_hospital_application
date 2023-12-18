package com.example.groupproject.bookappointment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;
import com.example.groupproject.adapter.SpacingBetweenAppointment;
import com.example.groupproject.adapter.TemplateAdapter;
import com.example.groupproject.choosedoctor.DoctorAdapter;
import com.example.groupproject.database.EmployeeProfile;
import com.example.groupproject.database.GetEmployeeInformation;
import com.example.groupproject.database.GetInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BookAppointment_Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SpacingBetweenAppointment spacingBetweenAppointment;
    private DoctorAdapter templateAdapter;
    private String specialty, patientUserId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_consultation_choosedoctor);

        Intent intent = getIntent();
        specialty = intent.getStringExtra("specialty");
        patientUserId = intent.getStringExtra("patientUserId");

        recyclerView = findViewById(R.id.doctor_placeholder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        spacingBetweenAppointment = new SpacingBetweenAppointment(getApplicationContext(), R.dimen.item_spacing);
        recyclerView.addItemDecoration(spacingBetweenAppointment);
        templateAdapter = new DoctorAdapter(getDoctor(specialty), patientUserId);
        recyclerView.setAdapter(templateAdapter);

    }
    private List<EmployeeProfile> getDoctor(String specialty) {
        GetEmployeeInformation.initializeEmployeeDatabase(getApplicationContext());
        return GetEmployeeInformation.getListProfileFromSpecialty(specialty, "D");
    }
}
