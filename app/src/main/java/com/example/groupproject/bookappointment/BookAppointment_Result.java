package com.example.groupproject.bookappointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.HideKeyBoard;
import com.example.groupproject.Home;
import com.example.groupproject.R;
import com.example.groupproject.Validation;
import com.example.groupproject.database.Appointment;
import com.example.groupproject.database.EmployeeProfile;
import com.example.groupproject.database.GetAppointment;
import com.example.groupproject.database.GetEmployeeInformation;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.message.Message_Chatting;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class BookAppointment_Result extends AppCompatActivity {
    private int meetingId;
    private Button backToHome;
    private TextView doctorName, doctorSpecialty, doctorCity, date, time, paymentStatus;
    private String paymentMethodString, patientId, status, role;
    private Appointment appointment;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_consultation_resultpage);

        Intent intent = getIntent();
        meetingId = intent.getIntExtra("meetingId", 0);
        status = intent.getStringExtra("status");
        role = intent.getStringExtra("role");

        GetAppointment.initializeDatabase(getApplicationContext());
        GetEmployeeInformation.initializeEmployeeDatabase(getApplicationContext());
        GetInformation.initializeDatabase(getApplicationContext());

        getAndSetInformation();

        backToHome = findViewById(R.id.backToHome);
        backToHome.setText(status);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("Back to home")){
                    Intent intent = new Intent(BookAppointment_Result.this, Home.class);
                    intent.putExtra("username", GetInformation.getUserName(patientId));
                    intent.putExtra("userid", patientId);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(BookAppointment_Result.this, Message_Chatting.class);
                    if (role.equals(patientId.substring(0, 1))){
                        intent.putExtra("userid", patientId);
                        intent.putExtra("receiverId", appointment.getDoctorId());
                    } else {
                        intent.putExtra("userid", appointment.getDoctorId());
                        intent.putExtra("receiverId", patientId);
                    }
                    startActivity(intent);
                }

            }
        });
    }

    private void getAndSetInformation(){
        doctorName = findViewById(R.id.doctor_name);
        doctorSpecialty = findViewById(R.id.doctor_specialty);
        doctorCity = findViewById(R.id.doctor_city);
        date = findViewById(R.id.dateSummary);
        time = findViewById(R.id.timeSummary);
        paymentStatus = findViewById(R.id.timeSummary4);

        appointment = GetAppointment.getAppointmentInformation(meetingId);
        patientId = appointment.getPatientId();
        String doctorId = appointment.getDoctorId();
        time.setText(appointment.getTime());
        date.setText(appointment.getDate());
        paymentStatus.setText(appointment.getStatus());
        if (role != null){
            if (role.equals("D")) {
                String patientNameString = GetInformation.getLastName(patientId) + " " + GetInformation.getFirstName(patientId);
                doctorName.setText(patientNameString);

                doctorSpecialty.setText("");
                doctorCity.setText(GetInformation.getCity(patientId));
            } else {
            String doctorNameString = GetInformation.getLastName(doctorId) + " " + GetInformation.getFirstName(doctorId);
            doctorName.setText(doctorNameString);

            EmployeeProfile employeeProfile = GetEmployeeInformation.getEmployeeProfile(doctorId);
            doctorSpecialty.setText(employeeProfile.getSpecialty());
            doctorCity.setText(GetInformation.getCity(doctorId));
        }
        } else {
            String doctorNameString = GetInformation.getLastName(doctorId) + " " + GetInformation.getFirstName(doctorId);
            doctorName.setText(doctorNameString);

            EmployeeProfile employeeProfile = GetEmployeeInformation.getEmployeeProfile(doctorId);
            doctorSpecialty.setText(employeeProfile.getSpecialty());
            doctorCity.setText(GetInformation.getCity(doctorId));
        }
    }
}
