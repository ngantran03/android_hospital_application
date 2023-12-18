package com.example.groupproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeProfile.class}, version = 2)
public abstract class EmployeeProfileDatabase extends RoomDatabase {
    public abstract EmployeeProfileDAO getEmployeeProfileDao();

    private static volatile EmployeeProfileDatabase INSTANCE;

    public static EmployeeProfileDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Profile.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EmployeeProfileDatabase.class,
                            "EmployeeProfileTable"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
