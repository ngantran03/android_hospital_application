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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class BookAppointment_Summary extends AppCompatActivity {
    private int meetingId;
    private Spinner paymentMethod;
    private ArrayAdapter<String> paymentAdapter;
    private Button confirm;
    private TextView doctorName, doctorSpecialty, doctorCity, date, time, paymentStatus;
    private TextInputLayout cardHolderLayout, cardNumberLayout, expireDateLayout, cvvLayout;
    private TextInputEditText cardHolder, cardNumber, expireDate, cvv;
    private String paymentMethodString, patientId;
    private Appointment appointment;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_consultation_summary);

        Intent intent = getIntent();
        meetingId = intent.getIntExtra("meetingId", 0);

        paymentMethod = findViewById(R.id.payment_method);
        cardHolderLayout = findViewById(R.id.card_holder_layout); cardNumberLayout = findViewById(R.id.card_number_layout); expireDateLayout = findViewById(R.id.expire_date_layout); cvvLayout = findViewById(R.id.cvv_layout);
        cardHolder = findViewById(R.id.card_holder); cardNumber = findViewById(R.id.card_number); expireDate = findViewById(R.id.expire_date); cvv = findViewById(R.id.cvv);
        cardHolderLayout.setVisibility(View.INVISIBLE); cardNumberLayout.setVisibility(View.INVISIBLE); expireDateLayout.setVisibility(View.INVISIBLE); cvvLayout.setVisibility(View.INVISIBLE);
        confirm = findViewById(R.id.backToHome);

//        clear error if focus
        Validation.reset(cardHolder, cardHolderLayout);
        Validation.reset(cardNumber, cardNumberLayout);
        Validation.reset(expireDate, expireDateLayout);
        Validation.reset(cvv, cvvLayout);

//      hide keyboard if out focus
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));


        GetAppointment.initializeDatabase(getApplicationContext());
        GetEmployeeInformation.initializeEmployeeDatabase(getApplicationContext());
        GetInformation.initializeDatabase(getApplicationContext());

        getAndSetInformation();

        paymentMethodString = "None";
        List<String> paymentMethodList = new ArrayList<>();
        paymentMethodList.add("None");
        paymentMethodList.add("Cash");
        paymentMethodList.add("Card");

        paymentAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_layout, paymentMethodList);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethod.setAdapter(paymentAdapter);

        paymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paymentMethodString = parent.getItemAtPosition(position).toString();
                if (paymentMethodString.equals("Card")){
                    cardHolderLayout.setVisibility(View.VISIBLE); cardNumberLayout.setVisibility(View.VISIBLE); expireDateLayout.setVisibility(View.VISIBLE); cvvLayout.setVisibility(View.VISIBLE);
                } else {
                    cardHolderLayout.setVisibility(View.INVISIBLE); cardNumberLayout.setVisibility(View.INVISIBLE); expireDateLayout.setVisibility(View.INVISIBLE); cvvLayout.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paymentMethodString.equals("None")){
                    Toast.makeText(BookAppointment_Summary.this, "Please select the payment method", Toast.LENGTH_SHORT).show();
                } else if (paymentMethodString.equals("Cash")){
                    Intent intent = new Intent(BookAppointment_Summary.this, BookAppointment_Result.class);
                    intent.putExtra("meetingId", meetingId);
                    intent.putExtra("status", "Back to home");
                    startActivity(intent);
                } else {
                    if (Validation.validateEmpty(cardHolder, cardHolderLayout) &&
                        Validation.validateEmpty(cardNumber, cardNumberLayout) &&
                        Validation.validateEmpty(expireDate, expireDateLayout) &&
                        Validation.validateEmpty(cvv, cvvLayout) &&
                        Validation.validateLength(cardNumberLayout, cardNumber, 16) &&
                        Validation.validateLength(cvvLayout, cvv, 3)){
                        GetAppointment.updateAppointment(new Appointment(appointment.getPatientId(), appointment.getDoctorId(), appointment.getTime(), appointment.getDate(), "Paid", appointment.getMeetingId()));
                        Intent intent = new Intent(BookAppointment_Summary.this, BookAppointment_Result.class);
                        intent.putExtra("meetingId", meetingId);
                        intent.putExtra("status", "Back to home");
                        startActivity(intent);
                    }
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

        String doctorNameString = GetInformation.getLastName(doctorId) + " " + GetInformation.getFirstName(doctorId);
        doctorName.setText(doctorNameString);

        EmployeeProfile employeeProfile = GetEmployeeInformation.getEmployeeProfile(doctorId);
        doctorSpecialty.setText(employeeProfile.getSpecialty());
        doctorCity.setText(GetInformation.getCity(doctorId));

    }
}
