package com.example.groupproject.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;
import com.example.groupproject.choosedoctor.DoctorHolder;
import com.example.groupproject.database.EmployeeProfile;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {
    private List<Message> messageList;
    private String userId;
    private static final int TYPE_VIEW_SENT = 1, TYPE_VIEW_RECEIVE = 2;
    public MessageAdapter(List<Message> messageList, String userId){
        this.messageList = messageList;
        this.userId = userId;
    }
    @Override
    public int getItemViewType(int position) {
        try {
            Message message = messageList.get(position);
            if (message != null && message.getSenderId().equals(userId)) {
                return TYPE_VIEW_SENT;
            } else {
                return TYPE_VIEW_RECEIVE;
            }
        } catch (IndexOutOfBoundsException e) {
            // Handle the exception, for example, log it or return a default value
            e.printStackTrace(); // Log the exception
            return TYPE_VIEW_SENT; // You can define a constant for the default type
        }
    }

    // Constructor and other methods
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == TYPE_VIEW_SENT){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_sent, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_received, parent, false);
        }
        // Create and return a new instance of your ViewHolder here
        return new MessageHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        // Bind data to the template views based on the position in the list
        Message message = messageList.get(position);

        // For example:
        holder.content.setText(message.getContent());
        holder.time.setText(message.getTime().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                // Handle other item click actions if needed
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the data list
        return messageList.size();
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
