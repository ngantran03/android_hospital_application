package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.ProfileDatabase;
import com.example.groupproject.forgotpass.ForgotPassword_SendCode;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LogIn extends AppCompatActivity {
    private TextInputEditText phoneNumber;
    private TextInputEditText passWord;
    private Button login, signup, forgotpass;
    private TextInputLayout phoneNumberLayout, passWordLayout;
    private String username, message, userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        if (message != null) {
            Toast.makeText(LogIn.this, message, Toast.LENGTH_SHORT).show();
        }
        phoneNumber = findViewById(R.id.login_phone);
        passWord = findViewById(R.id.code);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.sendcode);
        phoneNumberLayout = findViewById(R.id.phone_layout);
        passWordLayout = findViewById(R.id.code_layout);
        forgotpass = findViewById(R.id.forgotpass);

        GetInformation.initializeDatabase(getApplicationContext());

//        If focus, clear error
        Validation.reset(phoneNumber, phoneNumberLayout);
        Validation.reset(passWord, passWordLayout);

        // Set an OnTouchListener to detect touches outside of the EditText
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

        // Login button behaviour
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validatePhone(phoneNumber, phoneNumberLayout) && Validation.validateEmpty(passWord, passWordLayout)){
                    if (Validation.validateExists(passWordLayout, phoneNumber) && Validation.validateUser(passWordLayout, phoneNumber, passWord)){
                        userid = GetInformation.getUserId(phoneNumber.getText().toString());
                        username = GetInformation.getUserName(userid);
                        Intent intent = new Intent(LogIn.this, Home.class);
                        intent.putExtra("username", username);
                        intent.putExtra("userid", userid);
                        startActivity(intent);
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignUpActivity_ChooseRole.class);

                // Start the new activity
                startActivity(intent);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, ForgotPassword_SendCode.class);
                startActivity(intent);
            }
        });


    }

}
