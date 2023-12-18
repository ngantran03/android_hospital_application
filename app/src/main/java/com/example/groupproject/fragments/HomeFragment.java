package com.example.groupproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.Validation;
import com.example.groupproject.database.Appointment;
import com.example.groupproject.database.GetAppointment;
import com.example.groupproject.datamodel.AppointmentDataModel;
import com.example.groupproject.Patient_Urgent;
import com.example.groupproject.R;
import com.example.groupproject.adapter.SpacingBetweenAppointment;
import com.example.groupproject.adapter.TemplateAdapter;
import com.example.groupproject.service.Ambulance;
import com.example.groupproject.service.Assignment;
import com.example.groupproject.service.Checkin;
import com.example.groupproject.service.Consultation;
import com.example.groupproject.service.DoctorConsultation;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView username, service1, service2, service3;
    private Button urgent;
    private ImageButton buttonService1, buttonService2, buttonService3;
    private List<Appointment> dataList;
    private RecyclerView recyclerView;
    private TemplateAdapter templateAdapter;
    private SpacingBetweenAppointment spacingBetweenAppointment;
    private Bundle args;
    private String role, user, userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        urgent = view.findViewById(R.id.urgent_care);
        service1 = view.findViewById(R.id.service1); service2 = view.findViewById(R.id.service2); service3 = view.findViewById(R.id.service3);
        buttonService1 = view.findViewById(R.id.buttonService1); buttonService2 = view.findViewById(R.id.buttonService2); buttonService3 = view.findViewById(R.id.buttonService3);

//      retrive user name
        args = getArguments();
        if (args != null && args.containsKey("username") && args.containsKey("userid")) {
            user = args.getString("username");
            userid = args.getString("userid");
            username = view.findViewById(R.id.username); username.setText(user);
        }

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.appointment_layout_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        spacingBetweenAppointment = new SpacingBetweenAppointment(requireContext(), R.dimen.item_spacing); // R.dimen.item_spacing is a dimension resource for the spacing value
        recyclerView.addItemDecoration(spacingBetweenAppointment);

        role = userid.substring(0, 1);
        getData();

        // Initialize and set the adapter
        templateAdapter = new TemplateAdapter(dataList, role);
        recyclerView.setAdapter(templateAdapter);

        setService(role);

        urgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Patient_Urgent.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void setService(String role){
        if (role.equals("P")){
            service1.setText("Consultation"); service2.setText("Medicine"); service3.setText("Ambulence");
            buttonService1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Consultation.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
            buttonService2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Consultation.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
            buttonService3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Ambulance.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
        } else if (role.equals("N")){
            service1.setText("Check in"); service2.setText("Assignment"); service3.setVisibility(View.INVISIBLE);
            buttonService3.setVisibility(View.INVISIBLE);
            buttonService1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Checkin.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
            buttonService2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Assignment.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
        } else if (role.equals("D")){
            service1.setText("Consultation"); service2.setText("Assignment"); service3.setVisibility(View.INVISIBLE);
            buttonService3.setVisibility(View.INVISIBLE);
            buttonService1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), DoctorConsultation.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
            buttonService2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Assignment.class);
                    intent.putExtra("userid", userid);
                    startActivity(intent);
                }
            });
        } else {
            service1.setVisibility(View.INVISIBLE); service2.setVisibility(View.INVISIBLE); service3.setVisibility(View.INVISIBLE);
            buttonService1.setVisibility(View.INVISIBLE); buttonService2.setVisibility(View.INVISIBLE); buttonService3.setVisibility(View.INVISIBLE);
        }
    }

    private void getData(){
        dataList = new ArrayList<Appointment>();
        GetAppointment.initializeDatabase(getContext());
        if (role.trim().toString().equals("P") & !GetAppointment.getAppointmentPatient(userid).isEmpty()) {
            Appointment appointment = GetAppointment.getAppointmentPatient(userid).get(0);
            dataList.add(appointment);
        } else {
            if (!GetAppointment.getAppointmentDoctor(userid).isEmpty()){
                dataList.add(GetAppointment.getAppointmentDoctor(userid).get(0));
            }
        }
    }
}