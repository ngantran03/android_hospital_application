package com.example.groupproject.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.HideKeyBoard;
import com.example.groupproject.R;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.GetMessage;
import com.example.groupproject.database.Message;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Message_Chatting extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private String userId, receiverId, message;
    private List<Message> messageList;
    private ImageButton send;
    private TextInputEditText inputField;
    private TextView name, role;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chatting);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userid");
        receiverId = intent.getStringExtra("receiverId");

        recyclerView = findViewById(R.id.message_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        GetMessage.initializeDatabase(getApplicationContext());
        GetInformation.initializeDatabase(getApplicationContext());

        setReceiverInfo();
        getMessageList();

        HideKeyBoard.setupHideKeyboard(this, findViewById(android.R.id.content));

        adapter = new MessageAdapter(messageList, userId);
        recyclerView.setAdapter(adapter);
        inputField = findViewById(R.id.message_input);
        send = findViewById(R.id.imageButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = inputField.getText().toString();
                if (message.length() > 0){
                    String time = getCurrentTime();
                    GetMessage.createMessage(new Message(userId, receiverId, message, time));

                    getMessageList();
                    adapter = new MessageAdapter(messageList, userId);
                    recyclerView.setAdapter(adapter);
                    inputField.setText("");
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

//                    adapter.notifyItemInserted(messageList.size() - 1);
//
//                    // Scroll the RecyclerView to the last item
//                    recyclerView.smoothScrollToPosition(messageList.size() - 1);
//
//                    // Clear the input field
//                    inputField.setText("");
                }
            }
        });
    }

    private void getMessageList(){
        messageList = new ArrayList<>(GetMessage.getMessageOnUserId(userId));
    }

    private void setReceiverInfo(){
        name = findViewById(R.id.messagee_name);
        role = findViewById(R.id.message_role);

        name.setText(GetInformation.getLastName(receiverId) + " " + GetInformation.getFirstName(receiverId));
        if (receiverId.substring(0, 1).equals("P")){
            role.setText("Patient");
        } else {
            role.setText("Doctor");
        }
    }

    private String getCurrentTime(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a formatter for date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:mm");

        // Format the current date and time using the formatter
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
}
