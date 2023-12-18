package com.example.groupproject.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.HideKeyBoard;
import com.example.groupproject.Home;
import com.example.groupproject.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.groupproject.Validation;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.Profile;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class YourProfile extends AppCompatActivity {
    private String userid;
    private String firstName, lastName, city, district, address, phoneNumber, password, specialty;
    private int age;
    private TextInputLayout firstNameLayout, lastNameLayout, cityLayout, districtLayout, addressLayout, ageLayout;
    private TextInputEditText firstNameInput, lastNameInput, cityInput, districtInput, addressInput, ageInput;
    private Button updateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_yourprofile);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");

        firstNameLayout = findViewById(R.id.first_name_layout); lastNameLayout = findViewById(R.id.last_name_layout); cityLayout = findViewById(R.id.city_layout); districtLayout = findViewById(R.id.district_layout); addressLayout = findViewById(R.id.address_layout); ageLayout = findViewById(R.id.ageLayout);
        firstNameInput = findViewById(R.id.first_name); lastNameInput = findViewById(R.id.last_name); cityInput = findViewById(R.id.city); districtInput = findViewById(R.id.district); addressInput = findViewById(R.id.address); ageInput = findViewById(R.id.age);
        updateProfile = findViewById(R.id.update_profile);

//      If focus, clear error
        Validation.reset(firstNameInput, firstNameLayout); Validation.reset(lastNameInput, lastNameLayout); Validation.reset(cityInput, cityLayout); Validation.reset(districtInput, districtLayout); Validation.reset(addressInput, addressLayout); Validation.reset(ageInput, ageLayout);

        // Set an OnTouchListener to detect touches outside of the EditText
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

        GetInformation.initializeDatabase(getApplicationContext());
        getInfo();

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validateEmpty(firstNameInput, firstNameLayout) &
                    Validation.validateEmpty(lastNameInput, lastNameLayout) &
                    Validation.validateEmpty(cityInput, cityLayout) &
                    Validation.validateEmpty(districtInput, districtLayout) &
                    Validation.validateEmpty(addressInput, addressLayout) &
                    Validation.validateAge(ageInput, ageLayout))
                {
                        firstName = firstNameInput.getText().toString();
                        lastName = lastNameInput.getText().toString();
                        city = cityInput.getText().toString();
                        district = districtInput.getText().toString();
                        address = addressInput.getText().toString();
                        age = Integer.parseInt(ageInput.getText().toString());
                        phoneNumber = GetInformation.getPhoneNumber(userid);
                        password = GetInformation.getPassword(userid);
                        specialty = GetInformation.getSpecialty(userid);

                        if (GetInformation.updateProfile(new Profile(phoneNumber, firstName, lastName, city, district, address, specialty, password, userid, age))){
                            Toast.makeText(YourProfile.this, "Profile successfully updated", Toast.LENGTH_SHORT).show();
                        }
                }

            }
        });

    }

    private void getInfo(){
        firstName = GetInformation.getFirstName(userid); lastName = GetInformation.getLastName(userid); city = GetInformation.getCity(userid); district = GetInformation.getDistrict(userid); address = GetInformation.getAddress(userid); age = GetInformation.getAge(userid);
        firstNameInput.setText(firstName);
        lastNameInput.setText(lastName);
        cityInput.setText(city);
        districtInput.setText(district);
        addressInput.setText(address);
        ageInput.setText(String.valueOf(age));
    }
}