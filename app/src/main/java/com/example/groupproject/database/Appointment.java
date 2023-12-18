package com.example.groupproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Appointment")
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int id; // This is your index column
    @ColumnInfo(name = "MeetingId")
    private int meetingId;
    @ColumnInfo(name = "PatientID")
    private String patientId;
    @ColumnInfo(name = "DoctorID")
    private String doctorId;
    @ColumnInfo(name = "NurseID")
    private String nurseId;
    @ColumnInfo(name = "AppointmentTime")
    private String time;
    @ColumnInfo(name = "AppointmentDate")
    private String date;
    @ColumnInfo(name = "AppointmentStatus")
    private String status;

    public Appointment(){};

    public Appointment(String patientId, String doctorId, String time, String date, String status){
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.time = time;
        this.date = date;
        this.status = status;
        this.meetingId = GetAppointment.getIndex() + 1;
    }

    public Appointment(String patientId, String doctorId, String time, String date, String status, int meetingId){
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.time = time;
        this.date = date;
        this.status = status;
        this.meetingId = meetingId;
    }

    public void setId(int id){this.id = id;}
    public void setMeetingId(int meetingId){this.meetingId = meetingId;}
    public void setPatientId(String patientId){this.patientId = patientId;}
    public void setDoctorId(String doctorId){this.doctorId = doctorId;}
    public void setNurseId(String nurseId){this.nurseId = nurseId;}
    public void setTime(String time){this.time = time;}
    public void setDate(String date){this.date = date;}
    public void setStatus(String status){this.status = status;}
    public int getMeetingId(){return this.meetingId;}
    public String getPatientId(){return patientId;}
    public String getDoctorId(){return doctorId;}
    public String getNurseId(){return nurseId;}
    public String getTime(){return time;}
    public String getDate(){return date;}
    public String getStatus(){return status;}
    public int getId(){return id;}
}
