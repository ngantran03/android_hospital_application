package com.example.groupproject.forgotpass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.HideKeyBoard;
import com.example.groupproject.R;
import com.example.groupproject.SignUpActivity_Password;
import com.example.groupproject.Validation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword_OTP extends AppCompatActivity {
    private TextInputEditText code;
    private TextInputLayout codeLayout;
    private Button confirm;
    private String userid, otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass_otp);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        otp = intent.getStringExtra("otp");

        code = findViewById(R.id.code);
        codeLayout = findViewById(R.id.code_layout);
        confirm = findViewById(R.id.confirm);

//        If focus, clear error
        Validation.reset(code, codeLayout);

        // Set an OnTouchListener to detect touches outside of the EditText
        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validateEmpty(code, codeLayout)){
                    if (code.getText().toString().equals("123456")){
                        Intent intent = new Intent(ForgotPassword_OTP.this, SignUpActivity_Password.class);
                        intent.putExtra("userid", userid);
                        startActivity(intent);
                    } else {
                        codeLayout.setError("Wrong code");
                    }
                }
            }
        });
    }
}
