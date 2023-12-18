package com.example.groupproject.datamodel;

public class AppointmentDataModel {
    private String appointmentDate, appointmentTime, doctorName, doctorSpecial, patientName;
    private int meetingId, count = 0;

    public AppointmentDataModel(String appointmentDate, String appointmentTime, String doctorName, String doctorSpecial, String patientName){
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctorName = doctorName;
        this.doctorSpecial = doctorSpecial;
        this.patientName = patientName;
        meetingId();
    }

    private void meetingId(){
        count += 1;
        this.meetingId = count;
    }

    public String getDate(){
        return this.appointmentDate;
    }

    public String getTime(){
        return this.appointmentTime;
    }

    public String getDoctorName(){
        return this.doctorName;
    }

    public String getDoctorSpecial(){
        return this.doctorSpecial;
    }
    public String getPatientName(){return this.patientName;}
    public int getMeetingId(){return this.meetingId;}

}
