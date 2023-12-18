package com.example.groupproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class}, version = 3)
public abstract class MessageDatabase extends RoomDatabase {
    public abstract MessageDAO getMessageDAO();
    private static volatile MessageDatabase INSTANCE;
    public static MessageDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (Message.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MessageDatabase.class,
                            "Message"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
