package com.example.groupproject;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SignUpActivity_ChooseRole extends AppCompatActivity {
    private TextInputEditText employeeId;
    private Spinner spinner, spinnerSpecialty;
    private TextInputLayout employeeIdLayout;
    private Button next;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet = new ConstraintSet();
    private String roleType, specialty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_choose_role);

        spinner = (Spinner) findViewById(R.id.list_role);
        spinnerSpecialty = (Spinner) findViewById(R.id.list_specialty);
        next = findViewById(R.id.next);

// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_role,
                R.layout.spinner_layout
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    roleType = parent.getItemAtPosition(position).toString();
                    if (roleType.equals("Doctor") | roleType.equals("Nurse")){
                        spinnerSpecialty.setVisibility(view.VISIBLE);
                    }
                    else {
                        spinnerSpecialty.setVisibility(view.INVISIBLE);
                    }
                }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                employeeIdLayout.setVisibility(View.INVISIBLE);
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.specialty_list,
                R.layout.spinner_layout
        );
// Specify the layout to use when the list of choices appears.
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spinnerSpecialty.setAdapter(adapter2);

        spinnerSpecialty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specialty = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Set an OnTouchListener to detect touches outside of the EditText
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roleType.equals("Choose your role")){
                    Toast.makeText(SignUpActivity_ChooseRole.this, "Please select a valid role", Toast.LENGTH_SHORT).show();
                } else if (specialty.equals("Choose your specialty") && !roleType.equals("Patient")){
                    Toast.makeText(SignUpActivity_ChooseRole.this, "Please select a valid specialty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(SignUpActivity_ChooseRole.this, SignUpActivity_Information.class);
                    if (roleType.equals("Patient")){
                        intent.putExtra("role", roleType);
                        startActivity(intent);
                        // Start the new activity
                    } else {
                        intent.putExtra("role", roleType);
                        intent.putExtra("specialty", specialty);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}