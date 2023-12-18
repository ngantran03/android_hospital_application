package com.example.groupproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeProfileDAO {
    @Insert
    public void addProfile(EmployeeProfile employeeProfile);
    @Update
    public void updateProfile(EmployeeProfile employeeProfile);
    @Delete
    public void deleteProfile(EmployeeProfile employeeProfile);
    @Query("SELECT * FROM EmployeeProfileTable WHERE UserId = :userId")
    public EmployeeProfile getEmployeeProfile(String userId);
    @Query("UPDATE EmployeeProfileTable SET PatientTotal = :patientTotalNew WHERE UserId = :userId")
    public void updatePatientTotal(String userId, int patientTotalNew);
    @Query("UPDATE EmployeeProfileTable SET Rating = :ratingNew WHERE UserId = :userId")
    public void updateRating(String userId, float ratingNew);
    @Query("UPDATE EmployeeProfileTable SET Rating = :reviewNew WHERE UserId = :userId")
    public void updateReview(String userId, int reviewNew);
    @Query("SELECT * FROM EmployeeProfileTable WHERE Specialty = :specialty AND SUBSTRING(UserId, 1, 1) = :role")
    public List<EmployeeProfile> getEmployeeOnSpecialty(String specialty, String role);
}
