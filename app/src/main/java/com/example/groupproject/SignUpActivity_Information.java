package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.database.EmployeeProfile;
import com.example.groupproject.database.GetEmployeeInformation;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.Profile;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity_Information extends AppCompatActivity {
    private String role, userid, specialty, password;
    private String firstName, lastName, phoneNumber, city, district, address; private int age;
    private TextInputLayout firstNameLayout, lastNameLayout, phoneLayout, cityLayout, districtLayout, addressLayout, ageLayout;
    private TextInputEditText firstNameInput, lastNameInput, phoneInput, cityInput, districtInput, addressInput, ageInput;
    private Button submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_information);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        specialty = intent.getStringExtra("specialty");

        firstNameInput = findViewById(R.id.first_name); lastNameInput = findViewById(R.id.last_name); phoneInput = findViewById(R.id.phone_number); cityInput = findViewById(R.id.city); districtInput = findViewById(R.id.district); addressInput = findViewById(R.id.address); ageInput = findViewById(R.id.age);
        firstNameLayout = findViewById(R.id.first_name_layout); lastNameLayout = findViewById(R.id.last_name_layout); phoneLayout = findViewById(R.id.phone_number_layout); cityLayout = findViewById(R.id.city_layout); districtLayout = findViewById(R.id.district_layout); addressLayout = findViewById(R.id.address_layout); ageLayout = findViewById(R.id.ageLayout);
        submit = findViewById(R.id.submit);

//      clear error if focus
        Validation.reset(firstNameInput, findViewById(R.id.first_name_layout));
        Validation.reset(lastNameInput, findViewById(R.id.last_name_layout));
        Validation.reset(phoneInput, findViewById(R.id.phone_number_layout));
        Validation.reset(cityInput, findViewById(R.id.city_layout));
        Validation.reset(districtInput, findViewById(R.id.district_layout));
        Validation.reset(addressInput, findViewById(R.id.address_layout));
        Validation.reset(ageInput, findViewById(R.id.ageLayout));

//      hide keyboard if out focus
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

//      submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validation.validateEmpty(firstNameInput, firstNameLayout) &&
                                Validation.validateEmpty(lastNameInput, lastNameLayout) &&
                                Validation.validatePhone(phoneInput, phoneLayout) &&
                                Validation.validateEmpty(cityInput, cityLayout) &&
                                Validation.validateEmpty(districtInput, districtLayout) &&
                                Validation.validateEmpty(addressInput, addressLayout) &&
                                Validation.validateAge(ageInput, ageLayout)
                ) {
                    firstName = firstNameInput.getText().toString();
                    lastName = lastNameInput.getText().toString();
                    phoneNumber = phoneInput.getText().toString();
                    city = cityInput.getText().toString();
                    district = districtInput.getText().toString();
                    address = addressInput.getText().toString();
                    age = Integer.parseInt(ageInput.getText().toString());

                    userid = GetInformation.getUserId(phoneNumber);
                    if (userid != null){password = GetInformation.getPassword(userid);}
                    GetInformation.initializeDatabase(getApplicationContext());
                    if ((Validation.validateExistsNoError(phoneInput)) && (password != null)){
                        phoneLayout.setError("Phone number is already registered.");
                    } else {
                        if (!Validation.validateExistsNoError(phoneInput)) {
                            createProfile();
                        }
                        userid = GetInformation.getUserId(phoneNumber);
                        Intent intent = new Intent(SignUpActivity_Information.this, SignUpActivity_Password.class);
                        intent.putExtra("userid", userid);
                        // Start the new activity
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void createProfile() {
        String userid = role.substring(0, 1) + String.valueOf(GetInformation.getIndex() + 1);
        GetInformation.addProfile(new Profile(phoneNumber, firstName, lastName, city, district, address, specialty, null, userid, age));
    }
}

