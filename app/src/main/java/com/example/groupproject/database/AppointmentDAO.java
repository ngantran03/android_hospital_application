package com.example.groupproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppointmentDAO {
    @Insert
    public void addAppointment(Appointment appointment);
    @Update
    public void updateAppointment(Appointment appointment);
    @Delete
    public void deleteAppointment(Appointment appointment);
    @Query("SELECT * FROM Appointment WHERE MeetingId = :meetingId")
    public Appointment getAppointmentInformation(int meetingId);
    @Query("SELECT * FROM Appointment WHERE PatientID = :patientId")
    public List<Appointment> getAppointmentPatient(String patientId);
    @Query("SELECT * FROM Appointment WHERE DoctorID = :doctorId")
    public List<Appointment> getAppointmentDoctor(String doctorId);
    @Query("SELECT AppointmentTime FROM Appointment WHERE DoctorID = :doctorId AND AppointmentDate = :appointmentDate")
    public List<String> getAppointmentTimeDoctor(String doctorId, String appointmentDate);
    @Query("SELECT AppointmentTime FROM Appointment WHERE PatientID = :patientId AND AppointmentDate = :appointmentDate")
    public List<String> getAppointmentTimePatient(String patientId, String appointmentDate);
    @Query("SELECT AppointmentDate FROM Appointment WHERE PatientID = :patientId")
    public List<String> getAppointmentDatePatient(String patientId);
    @Query("SELECT AppointmentDate FROM Appointment WHERE DoctorID = :doctorId")
    public List<String> getAppointmentDateDoctor(String doctorId);
    @Query("SELECT MAX(id) FROM Appointment")
    public int getMaxIndex();
    @Query("SELECT id From Appointment WHERE MeetingId = :meetingId")
    public int getId(int meetingId);
    @Query("SELECT * FROM Appointment WHERE " +
            "SUBSTR(AppointmentDate, 12, 4) < SUBSTR(:currentDate, 12, 4) AND " +
            "SUBSTR(AppointmentDate, 9, 2) < SUBSTR(:currentDate, 9, 2) AND " +
            "SUBSTR(AppointmentDate, 6, 2) < SUBSTR(:currentDate, 6, 2) AND " +
            "PatientID = :userid")
    public List<Appointment> getPastAppointmentPatient(String userid, String currentDate);
    @Query("SELECT * FROM Appointment WHERE " +
            "SUBSTR(AppointmentDate, 12, 4) < SUBSTR(:currentDate, 12, 4) AND " +
            "SUBSTR(AppointmentDate, 9, 2) < SUBSTR(:currentDate, 9, 2) AND " +
            "SUBSTR(AppointmentDate, 6, 2) < SUBSTR(:currentDate, 6, 2) AND " +
            "DoctorID = :userid")
    public List<Appointment> getPastAppointmentDoctor(String userid, String currentDate);
    @Query("SELECT * FROM Appointment WHERE " +
            "SUBSTR(AppointmentDate, 12, 4) >= SUBSTR(:currentDate, 12, 4) AND " +
            "SUBSTR(AppointmentDate, 9, 2) >= SUBSTR(:currentDate, 9, 2) AND " +
            "SUBSTR(AppointmentDate, 6, 2) >= SUBSTR(:currentDate, 6, 2) AND " +
            "PatientID = :userid")
    public List<Appointment> getUpcomingAppointmentPatient(String userid, String currentDate);
    @Query("SELECT * FROM Appointment WHERE " +
            "SUBSTR(AppointmentDate, 12, 4) >= SUBSTR(:currentDate, 12, 4) AND " +
            "SUBSTR(AppointmentDate, 9, 2) >= SUBSTR(:currentDate, 9, 2) AND " +
            "SUBSTR(AppointmentDate, 6, 2) >= SUBSTR(:currentDate, 6, 2) AND " +
            "DoctorID = :userid")
    public List<Appointment> getUpcomingAppointmentDoctor(String userid, String currentDate);
    @Query("SELECT SUBSTR(AppointmentDate, 7, 4) AS yearPart FROM Appointment")
    public List<String> getYearParts();
}
