package com.example.groupproject.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.groupproject.R;
import com.example.groupproject.database.Appointment;
import com.example.groupproject.database.GetAppointment;
import com.example.groupproject.datamodel.AppointmentDataModel;
import com.example.groupproject.adapter.SpacingBetweenAppointment;
import com.example.groupproject.adapter.TemplateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentFragment extends Fragment {
    private Button upcoming, past;
    private RecyclerView recyclerView;
    private TemplateAdapter templateAdapter;
    private SpacingBetweenAppointment spacingBetweenAppointment;
    private String role, userid;
    private List<Appointment> pastAppointment, upcomingAppointment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        upcoming = view.findViewById(R.id.upcoming);
        past = view.findViewById(R.id.past);

        Bundle args = getArguments();
        userid = args.getString("userid");
        role = userid.substring(0, 1);
        GetAppointment.initializeDatabase(getContext());

        getUpcomingAppointment();
        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.appointment_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        spacingBetweenAppointment = new SpacingBetweenAppointment(requireContext(), R.dimen.item_spacing); // R.dimen.item_spacing is a dimension resource for the spacing value
        recyclerView.addItemDecoration(spacingBetweenAppointment);
        // Initialize and set the adapter
        if (upcomingAppointment.isEmpty()){
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            templateAdapter = new TemplateAdapter(upcomingAppointment, role);
            recyclerView.setAdapter(templateAdapter);
        }
        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                past.setBackgroundColor(Color.TRANSPARENT);
                past.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));

                upcoming.setBackgroundResource(R.drawable.button_schedule);
                upcoming.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                templateAdapter = new TemplateAdapter(upcomingAppointment, role);
                recyclerView.setAdapter(templateAdapter);
            }
        });

        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPastAppointment();
                upcoming.setBackgroundColor(Color.TRANSPARENT);
                upcoming.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));

                past.setBackgroundResource(R.drawable.button_schedule);
                past.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
                templateAdapter = new TemplateAdapter(pastAppointment, role);
                recyclerView.setAdapter(templateAdapter);
            }
        });
        return view;
    }
    private void getPastAppointment() {
        if (role.equals("P")) {
            pastAppointment = new ArrayList<>(GetAppointment.getPastAppointmentPatient(userid));
        } else {
            pastAppointment = new ArrayList<>(GetAppointment.getPastAppointmentDoctor(userid));
        }
    }

    private void getUpcomingAppointment(){
        if (role.equals("P")) {
            upcomingAppointment = new ArrayList<>(GetAppointment.getUpcomingAppointmentPatient(userid));
        } else {
            upcomingAppointment = new ArrayList<>(GetAppointment.getUpcomingAppointmentDoctor(userid));
        }
    }
}