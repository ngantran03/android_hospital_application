package com.example.groupproject;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.room.Room;

import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.Profile;
import com.example.groupproject.database.ProfileDAO;
import com.example.groupproject.database.ProfileDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Validation {
    public static void reset(TextInputEditText input, TextInputLayout layout) {
        input.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                layout.setError(null);
            }
        });
    }

    public static boolean validateAge(TextInputEditText ageInput, TextInputLayout ageLayout) {
        try {
            String ageString = ageInput.getText().toString();
            int age = Integer.parseInt(ageString);

            if (!validateEmpty(ageInput, ageLayout)) {
                return false;
            } else if (age < 1) {
                ageLayout.setError("Invalid age");
                return false;
            } else {
                ageLayout.setError(null);
                return true;
            }
        } catch (NumberFormatException e) {
            ageLayout.setError("Invalid age");
            return false;
        }
    }

    public static boolean validatePhone(TextInputEditText phoneInput, TextInputLayout phoneLayout) {
        String phone = phoneInput.getText().toString();
        try {
            if (!validateEmpty(phoneInput, phoneLayout)) {
                return false;
            } else if (phone.length() != 9) {
                phoneLayout.setError("Wrong phone number");
                return false;
            } else {
                phoneLayout.setError(null);
                return true;
            }
        } catch (NumberFormatException e) {
            phoneLayout.setError("Invalid age");
            return false;
        }
    }

    public static boolean validateEmpty(TextInputEditText stringInput, TextInputLayout stringLayout) {
        if (stringInput.getText().toString().isEmpty()) {
            stringLayout.setError("Please fill out the information");
            return false;
        } else {
            stringLayout.setError(null);
            return true;
        }
    }

    public static boolean validateExists(TextInputLayout passwordLayout, TextInputEditText phoneInput){
        if (!GetInformation.checkUserExist(phoneInput.getText().toString())){
            passwordLayout.setError("Wrong information. Please retry");
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateExistsNoError(TextInputEditText phoneInput){
        if (!GetInformation.checkUserExist(phoneInput.getText().toString())){
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateUser(TextInputLayout passwordLayout, TextInputEditText phoneInput, TextInputEditText passwordInput){
        String password = GetInformation.getPasswordFromPhone(phoneInput.getText().toString());
        if (password == null){
            passwordLayout.setError("Account has ");
            return false;
        }
        if (password.equals(passwordInput.getText().toString())){
            return true;
        } else {
            passwordLayout.setError("Wrong information. Please retry");
            return false;
        }
    }

    public static boolean validateLength(TextInputLayout layout, TextInputEditText input, int length){
        String inputString = input.getText().toString();
        if (inputString.length() == length){
            return true;
        } else {
            layout.setError("Wrong information. Please retry");
            return false;
        }
    }
}
