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

public class SignUpActivity_Password extends AppCompatActivity{
    private String firstName, lastName, phoneNumber, city, district, address, password, confirmPassword, userid, specialty; private int age;
    private TextInputEditText passwordInput, confirmPasswordInput;
    private TextInputLayout passwordLayout, confirmPasswordLayout;
    private Button confirm;
    private Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_password);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        passwordInput = findViewById(R.id.timeInput); confirmPasswordInput = findViewById(R.id.confirmPassInput);
        passwordLayout = findViewById(R.id.dayLayout); confirmPasswordLayout = findViewById(R.id.confirmPassLayout);
        confirm = findViewById(R.id.confirm);

//      reset
        Validation.reset(passwordInput, passwordLayout);
        Validation.reset(confirmPasswordInput, confirmPasswordLayout);

//      hide keyboard
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

//      confirm button
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validateEmpty(passwordInput, passwordLayout) && Validation.validateEmpty(confirmPasswordInput, confirmPasswordLayout)){
                    password = passwordInput.getText().toString(); confirmPassword = confirmPasswordInput.getText().toString();
                    if (!password.equals(confirmPassword)){
                        confirmPasswordLayout.setError("The confirmed password must be matched");
                    } else {
                        GetInformation.initializeDatabase(getApplicationContext());
                        getInformation();
                        if (GetInformation.updateProfile(new Profile(phoneNumber, firstName, lastName, city, district, address, specialty, password, userid, age))){
                            if (userid != null && !userid.substring(0, 1).equals("P")){
                                GetEmployeeInformation.initializeEmployeeDatabase(getApplicationContext());
                                GetEmployeeInformation.addEmployeeProfile(new EmployeeProfile(userid, specialty));
                            }
                            Intent intent = new Intent(SignUpActivity_Password.this, LogIn.class);
                            intent.putExtra("message", "Account created successfully. Please log in to continue.");
                            startActivity(intent);
                        }
                    }
                }

            }
        });
    }

    private void getInformation(){
        firstName = GetInformation.getFirstName(userid);
        lastName = GetInformation.getLastName(userid);
        phoneNumber = GetInformation.getPhoneNumber(userid);
        city = GetInformation.getCity(userid);
        district = GetInformation.getDistrict(userid);
        address = GetInformation.getAddress(userid);
        age = GetInformation.getAge(userid);
        specialty = GetInformation.getSpecialty(userid);
        password = passwordInput.getText().toString();
    }
}
