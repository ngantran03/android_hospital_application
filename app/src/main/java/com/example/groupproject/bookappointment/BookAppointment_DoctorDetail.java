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

import com.example.groupproject.R;
import com.example.groupproject.database.Appointment;
import com.example.groupproject.database.EmployeeProfile;
import com.example.groupproject.database.GetAppointment;
import com.example.groupproject.database.GetEmployeeInformation;
import com.example.groupproject.database.GetInformation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;


public class BookAppointment_DoctorDetail extends AppCompatActivity {
    private String doctorUserId, patientUserId, selectedDate, selectedTime;
    private List<String> time, date;
    private Spinner dateSpinner, timeSpinner;
    private ArrayAdapter<String> dateAdapter, timeAdapter;
    private Button makeAppointment;
    private TextView doctorName, doctorSpecialty, doctorCity;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_consultation_doctordetail);

        dateSpinner = findViewById(R.id.date);
        timeSpinner = findViewById(R.id.time);
        makeAppointment = findViewById(R.id.backToHome);

        Intent intent = getIntent();
        doctorUserId = intent.getStringExtra("doctorUserId");
        patientUserId = intent.getStringExtra("patientUserId");

        GetAppointment.initializeDatabase(getApplicationContext());

        getInformation();
        getDate();
        getDefaultSchedule();

        dateAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_layout, date);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDate = parent.getItemAtPosition(position).toString();
                if (!selectedDate.equals("None")){
                    getSchedule(doctorUserId, selectedDate);
                    updateTimeSpinner();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        timeAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_layout, time);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        selectedTime = "None"; selectedDate = "None";

        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((selectedDate.equals("None")) & (selectedTime.equals("None"))){
                    Toast.makeText(BookAppointment_DoctorDetail.this, "Please select the date and time", Toast.LENGTH_SHORT).show();
                } else if (GetAppointment.checkTimeBookedPatient(patientUserId, selectedDate, selectedTime)){
                    Toast.makeText(BookAppointment_DoctorDetail.this, "You already have another section at this time. Please select another one.", Toast.LENGTH_SHORT).show();
                } else if (GetAppointment.checkDoubleBookingDoctor(doctorUserId, selectedDate, selectedTime)){
                    Toast.makeText(BookAppointment_DoctorDetail.this, "Doctor already has another section at this time. Please select another one.", Toast.LENGTH_SHORT).show();
                } else {
                    int meetingId = createAppointment();
                    Intent intent = new Intent(BookAppointment_DoctorDetail.this, BookAppointment_Summary.class);
                    intent.putExtra("meetingId", meetingId);
                    startActivity(intent);
                }
            }
        });
    }

    private void getDefaultSchedule(){
        time = new ArrayList<>();
        time.add("None");
    }

    private void getSchedule(String doctorUserId, String selectedDate){
        time = new ArrayList<>();
        time.add("None");
        for (int i = 9; i <= 17; i++){
            if (i != 12){
                time.add(Integer.toString(i) + ":00");
            }
        }
        time = GetAppointment.checkTimeAvailable(patientUserId, doctorUserId, selectedDate, time);
    }

    private void getDate(){
        date = new ArrayList<>();
        Date currentDate = new Date();
        date.add("None");

        // Get the start date of the week
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        // Format the dates and add them to the list
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/YYYY", Locale.getDefault());
        for (int i = 0; i < 7; i++) {
            date.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, 1);  // Add one day
        }


    }

    private void updateTimeSpinner() {
        timeAdapter.clear();
        timeAdapter.addAll(time);
        timeAdapter.notifyDataSetChanged();
    }

    private int createAppointment(){
        return GetAppointment.addAppointment(new Appointment(patientUserId, doctorUserId, selectedTime, selectedDate, "Unpaid"));
    }

    private void getInformation(){
        GetEmployeeInformation.initializeEmployeeDatabase(getApplicationContext());
        GetInformation.initializeDatabase(getApplicationContext());

        EmployeeProfile employeeProfile = GetEmployeeInformation.getEmployeeProfile(doctorUserId);

        doctorName = findViewById(R.id.doctor_name);
        doctorName.setText(GetInformation.getLastName(doctorUserId) + " " + GetInformation.getFirstName(doctorUserId));
        doctorSpecialty = findViewById(R.id.doctor_specialty);
        doctorSpecialty.setText(employeeProfile.getSpecialty());
        doctorCity = findViewById(R.id.doctor_city);
        doctorCity.setText(GetInformation.getCity(doctorUserId));
    }
}
