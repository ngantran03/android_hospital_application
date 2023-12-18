package com.example.groupproject.database;

import android.content.Context;

import java.util.List;

public class GetEmployeeInformation {
    private static EmployeeProfileDAO employeeDao;

    public static void initializeEmployeeDatabase(Context context) {
        EmployeeProfileDatabase db = EmployeeProfileDatabase.getDatabase(context);
        employeeDao = db.getEmployeeProfileDao();
    }
    public static boolean addEmployeeProfile(EmployeeProfile employeeProfile){
        employeeDao.addProfile(employeeProfile);
        return true;
    }

    public static List<EmployeeProfile> getListProfileFromSpecialty(String specialty, String role){
        return employeeDao.getEmployeeOnSpecialty(specialty, role.substring(0, 1));
    }

    public static EmployeeProfile getEmployeeProfile(String userId){
        return employeeDao.getEmployeeProfile(userId);
    }
}
