package com.example.groupproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDAO {
    @Insert
    public void addMessage(Message message);
    @Update
    public void updateMessage(Message message);
    @Delete
    public void deleteMessage(Message message);
    @Query("SELECT MAX(id) FROM Message")
    public int getMaxIndex();
    @Query("SELECT * FROM Message WHERE Sender = :userId OR Receiver = :userId")
    public List<Message> getMessageOnUser(String userId);


}
