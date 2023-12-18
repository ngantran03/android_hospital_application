package com.example.groupproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Entity(tableName = "EmployeeProfileTable")
public class EmployeeProfile {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "UserId")
    private String userid;
    @ColumnInfo(name = "Specialty")
    private String specialty;
    @ColumnInfo(name = "PatientTotal")
    private int patientTotal;
    @ColumnInfo(name = "Review")
    private int review;
    @ColumnInfo(name = "Rating")
    private float rating;

    public EmployeeProfile(String userid, String specialty){
        this.userid = userid;
        this.specialty = specialty;
        this.patientTotal = 0;
        this.review = 0;
        this.rating = 0;
    }

    public void addPatient(){
        this.patientTotal += 1;
    }
    public void addRating(int rating){
        float totalPoint = this.rating*this.review + rating;
        this.review += 1;
        this.rating = totalPoint/review;
    }

    public void setUserid(String userid){this.userid = userid;}
    public String getUserid(){return userid;}
    public void setSpecialty(String specialty){this.specialty = specialty;}
    public String getSpecialty(){return specialty;}
    public void setPatientTotal(int patientTotal){this.patientTotal = patientTotal;}
    public int getPatientTotal(){return patientTotal;}
    public void setReview(int review){this.review = review;}
    public int getReview(){return review;}
    public void setRating(float rating){this.rating = rating;}
    public float getRating(){return rating;}
    public String getUserIdFromProfile(){return this.userid;}
    public String getSpecialtyFromProfile(){return this.specialty;}
}
