package com.example.groupproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDAO {
    @Insert
    public void addProfile(Profile profile);
    @Update
    public void updateProfile(Profile profile);
    @Delete
    public void deleteProfile(Profile profile);

    @Query("SELECT COUNT(*) FROM UserProfile WHERE `PhoneNumber` = :phoneNumber")
    public int doesUserIdExist(String phoneNumber);
    @Query("SELECT Password FROM UserProfile WHERE `UserId` = :userId")
    public String getPassword(String userId);
    @Query("SELECT UserId FROM UserProfile WHERE `PhoneNumber` = :phoneNumber")
    public String getUserId(String phoneNumber);
    @Query("SELECT * FROM UserProfile WHERE `UserId` = :userId")
    public Profile getProfile(String userId);
    @Query("SELECT FirstName FROM UserProfile WHERE `userId` = :userId")
    public String getFirstName(String userId);
    @Query("SELECT LastName FROM UserProfile WHERE `UserId` = :userId")
    public String getLastName(String userId);
    @Query("SELECT City FROM UserProfile WHERE UserId = :userId")
    public String getCity(String userId);
    @Query("SELECT District FROM UserProfile WHERE UserId = :userId")
    public String getDistrict(String userId);
    @Query("SELECT Address FROM UserProfile WHERE UserId = :userId")
    public String getAddress(String userId);
    @Query("SELECT Age FROM UserProfile WHERE UserId = :userId")
    public int getAge(String userId);
    @Query("SELECT PhoneNumber FROM UserProfile WHERE UserId = :userId")
    public String getPhoneNumber(String userId);
    @Query("SELECT MAX(id) FROM UserProfile")
    public int getMaxIndex();
    @Query("SELECT id FROM UserProfile WHERE UserId = :userId")
    public int getId(String userId);
    @Query("SELECT specialty FROM UserProfile WHERE UserId = :userId")
    public String getSpecialty(String userId);
    @Query("SELECT UserId FROM UserProfile WHERE Specialty = :specialty")
    public List<String> getDoctorOnSpecialty(String specialty);
}
