package com.example.groupproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Profile.class}, version = 4)
public abstract class ProfileDatabase extends RoomDatabase {
    public abstract ProfileDAO getProfileDao();

    private static volatile ProfileDatabase INSTANCE;

    public static ProfileDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Profile.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ProfileDatabase.class,
                            "UserProfile"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}