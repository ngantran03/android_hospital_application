package com.example.groupproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Appointment.class}, version = 7)
public abstract class AppointmentDatabase extends RoomDatabase{
    public abstract AppointmentDAO getAppointmentDAO();
    private static volatile AppointmentDatabase INSTANCE;
    public static AppointmentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Appointment.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppointmentDatabase.class,
                            "Appointment"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
