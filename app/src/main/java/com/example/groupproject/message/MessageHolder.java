package com.example.groupproject.message;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;

public class MessageHolder extends RecyclerView.ViewHolder {
    private static final int TYPE_VIEW_SENT = 1, TYPE_VIEW_RECEIVE = 2;
    public TextView content, time;
    public MessageHolder(@NonNull View itemView, int viewType) {
        super(itemView);
        if (viewType == TYPE_VIEW_SENT){
            content = itemView.findViewById(R.id.message_sent_content);
            content.setPadding(30, 20, 30, 20);
            time = itemView.findViewById(R.id.message_sent_time);
        } else {
            content = itemView.findViewById(R.id.message_received_content);
            content.setPadding(30, 20, 30, 20);
            time = itemView.findViewById(R.id.message_received_time);
        }

    }
}
