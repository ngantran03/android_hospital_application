package com.example.groupproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "Message")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "MessageId")
    private int messageId;
    @ColumnInfo(name = "Sender")
    private String senderId;
    @ColumnInfo(name = "Receiver")
    private String receiverId;
    @ColumnInfo(name = "Content")
    private String content;
    @ColumnInfo(name = "Time")
    private String time;
    public Message() {
    }
    public Message(String senderId, String receiverId, String content, String time){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.time = time;
        this.messageId = GetMessage.getIndex() + 1;
    }

    public void setId(int id){this.id = id;}
    public void setMessageId(int id){this.messageId = id;}
    public void setSenderId(String id){this.senderId = id;}
    public void setReceiverId(String receiverId){this.receiverId = receiverId;}
    public void setTime(String time){this.time = time;}
    public void setContent(String content){this.content = content;}

    public int getId(){return this.id;}
    public int getMessageId(){return this.messageId;}
    public String getSenderId(){return this.senderId;}
    public String getReceiverId(){return this.receiverId;}
    public String getContent(){return this.content;}
    public String getTime(){return this.time;}
}
