package com.example.groupproject.database;

import android.content.Context;

import java.util.List;

public class GetMessage {
    private static MessageDAO messageDao;
    public static void initializeDatabase(Context context){
        MessageDatabase db = MessageDatabase.getDatabase(context);
        messageDao = db.getMessageDAO();
    }
    public static int createMessage(Message message){
        messageDao.addMessage(message);
        return message.getMessageId();
    }
    public static int getIndex(){return messageDao.getMaxIndex();}
    public static List<Message> getMessageOnUserId(String userId){
        return messageDao.getMessageOnUser(userId);
    }

}
