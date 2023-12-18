package com.example.groupproject.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.groupproject.Validation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class GetInformation {
    private static ProfileDAO profileDao;

    public static void initializeDatabase(Context context) {
        ProfileDatabase db = ProfileDatabase.getDatabase(context);
        profileDao = db.getProfileDao();
    }

    public static boolean checkUserExist(String phoneNumber){
        return profileDao.doesUserIdExist(phoneNumber) > 0;
    }
    public static String getPasswordFromPhone(String phoneNumber){
        String userid = profileDao.getUserId(phoneNumber);
        return profileDao.getPassword(userid);
    }
    public static String getUserId(String phoneNumber){
        return profileDao.getUserId(phoneNumber);
    }
    public static String getUserName(String userid){
        return profileDao.getLastName(userid) + " " + profileDao.getFirstName(userid);
    }
    public static String getFirstName(String userid){
        return profileDao.getFirstName(userid);
    }
    public static String getLastName(String userid){
        return profileDao.getLastName(userid);
    }
    public static String getCity(String userid){
        return profileDao.getCity(userid);
    }
    public static String getDistrict(String userid){
        return profileDao.getDistrict(userid);
    }
    public static String getAddress(String userid){
        return profileDao.getAddress(userid);
    }
    public static int getAge(String userid){
        return profileDao.getAge(userid);
    }
    public static boolean updateProfile(Profile profile) {
        int id = profileDao.getId(profile.getUserId());
        profile.setId(id);
        profileDao.updateProfile(profile);
        return true;
    }
    public static boolean addProfile (Profile profile){
        profileDao.addProfile(profile);
        return true;
    }
    public static String getPhoneNumber(String userid){return profileDao.getPhoneNumber(userid);}
    public static String getPassword(String userid){return profileDao.getPassword(userid);}
    public static int getIndex(){return profileDao.getMaxIndex();}
    public static String getSpecialty(String userid){return profileDao.getSpecialty(userid);}
    public static List<String> getDoctorOnSpecialty(String specialty){return profileDao.getDoctorOnSpecialty(specialty);}
}
