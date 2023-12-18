package com.example.groupproject.database;

import android.content.Context;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GetAppointment {
    public static AppointmentDAO appointmentDAO;

    public static int addAppointment(Appointment appointment){
        appointmentDAO.addAppointment(appointment);
        return appointment.getMeetingId();
    }

    public static void initializeDatabase(Context context){
        AppointmentDatabase appointmentDatabase = AppointmentDatabase.getDatabase(context);
        appointmentDAO = appointmentDatabase.getAppointmentDAO();
    }

    public static List<String> checkTimeAvailable(String patientId, String doctorId, String date, List<String> time){
        List<String> bookedTimeDoctor = appointmentDAO.getAppointmentTimeDoctor(doctorId, date);
        List<String> bookedTimePatient = appointmentDAO.getAppointmentTimePatient(patientId, date);
        List<String> availableTime = new ArrayList<>(time);
        availableTime.removeAll(bookedTimeDoctor);
        availableTime.removeAll(bookedTimePatient);
        return availableTime;
    }

    public static Appointment getAppointmentInformation(int meetingId){
        return appointmentDAO.getAppointmentInformation(meetingId);
    }

    public static boolean checkTimeBookedPatient(String patientId, String date, String time){
        List<String> bookedTime = appointmentDAO.getAppointmentTimePatient(patientId, date);
        return bookedTime.contains(time);
    }

    public static boolean checkDoubleBookingDoctor(String doctorId, String date, String time){
        List<String> bookedTime = appointmentDAO.getAppointmentTimeDoctor(doctorId, date);
        return bookedTime.contains(time);
    }
    public static int getIndex(){return appointmentDAO.getMaxIndex();}
    public static void updateAppointment(Appointment appointment){
        int id = appointmentDAO.getId(appointment.getMeetingId());
        appointment.setId(id);
        appointmentDAO.updateAppointment(appointment);
    }
    public static List<Appointment> getAppointmentPatient(String patientId){
        return appointmentDAO.getAppointmentPatient(patientId);
    }
    public static List<Appointment> getAppointmentDoctor(String doctorId){
        return appointmentDAO.getAppointmentDoctor(doctorId);
    }

    public static List<Appointment> getPastAppointmentPatient(String patientId){
        Date currentDate = new Date();

        // Format the current date to match your appointment date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault());
        String formattedCurrentDate = dateFormat.format(currentDate);

        // Get past appointments based on the formatted current date
        List<Appointment> pastAppointments = appointmentDAO.getPastAppointmentPatient(patientId, formattedCurrentDate);

        return pastAppointments;
    }
    public static List<Appointment> getPastAppointmentDoctor(String doctorId){
        Date currentDate = new Date();

        // Format the current date to match your appointment date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault());
        String formattedCurrentDate = dateFormat.format(currentDate);

        // Get past appointments based on the formatted current date
        List<Appointment> pastAppointments = appointmentDAO.getPastAppointmentDoctor(doctorId, formattedCurrentDate);

        return pastAppointments;
    }
    public static List<Appointment> getUpcomingAppointmentPatient(String patientId){
        Date currentDate = new Date();

        // Format the current date to match your appointment date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault());
        String formattedCurrentDate = dateFormat.format(currentDate);

        // Get past appointments based on the formatted current date
        List<Appointment> upcommingAppointment = appointmentDAO.getUpcomingAppointmentPatient(patientId, formattedCurrentDate);

        return upcommingAppointment;
    }
    public static List<Appointment> getUpcomingAppointmentDoctor(String doctorId){
        Date currentDate = new Date();

        // Format the current date to match your appointment date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault());
        String formattedCurrentDate = dateFormat.format(currentDate);

        // Get past appointments based on the formatted current date
        List<Appointment> upcommingAppointment = appointmentDAO.getUpcomingAppointmentDoctor(doctorId, formattedCurrentDate);

        return upcommingAppointment;
    }
}
