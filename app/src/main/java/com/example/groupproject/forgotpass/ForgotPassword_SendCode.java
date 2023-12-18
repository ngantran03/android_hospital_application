package com.example.groupproject.forgotpass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.HideKeyBoard;
import com.example.groupproject.R;
import com.example.groupproject.Validation;
import com.example.groupproject.database.GetInformation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword_SendCode extends AppCompatActivity {
    private TextInputEditText phoneNumber;
    private TextInputLayout phoneNumberLayout;
    private Button sendcode;
    private String userid, otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_resendcode);

        phoneNumber = findViewById(R.id.login_phone);
        phoneNumberLayout = findViewById(R.id.phone_layout);
        sendcode = findViewById(R.id.sendcode);

//        If focus, clear error
        Validation.reset(phoneNumber, phoneNumberLayout);

        // Set an OnTouchListener to detect touches outside of the EditText
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

        GetInformation.initializeDatabase(getApplicationContext());

        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validatePhone(phoneNumber, phoneNumberLayout) && Validation.validateExists(phoneNumberLayout, phoneNumber)){
                    otp = "123456"; userid = GetInformation.getUserId(phoneNumber.getText().toString());
                    Intent intent = new Intent(ForgotPassword_SendCode.this, ForgotPassword_OTP.class);
                    intent.putExtra("userid", userid);
                    intent.putExtra("otp", otp);
                    startActivity(intent);
                }
            }
        });
    }
}
