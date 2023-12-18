package com.example.groupproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.appointment.AppointmentDetail;
import com.example.groupproject.R;
import com.example.groupproject.bookappointment.BookAppointment_Result;
import com.example.groupproject.database.Appointment;

import java.util.List;

public class TemplateViewHolder extends RecyclerView.ViewHolder {
    // Define the views in your template layout
    public TextView appointmentDateInput, appointmentTimeInput, doctorNameInput, doctorSpecInput, appointmentId;
    public Button click;
    private List<Appointment> dataList;
    private String role;

    public TemplateViewHolder(List<Appointment> dataList, View itemView, String role) {
        super(itemView);
        this.dataList = dataList;
        this.role = role;
        // Initialize your views here
        // For example:
        appointmentDateInput = itemView.findViewById(R.id.appointment_date);
        appointmentTimeInput = itemView.findViewById(R.id.appointment_hour);
        doctorNameInput = itemView.findViewById(R.id.appointment_doctorname);
        doctorSpecInput = itemView.findViewById(R.id.appointment_doctorspecial);
        click = itemView.findViewById(R.id.click);
//        appointmentId = itemView.findViewById(R.id.appointment_id);

        // Set the click listener for the entire item
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event here, you can get the meetingId or any other data
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    int meetingId = dataList.get(position).getMeetingId();
                    // Now, you can use the meetingId or perform any other actions
                    // For example, start a new activity with the meetingId
                    Intent intent = new Intent(itemView.getContext(), BookAppointment_Result.class);
                    intent.putExtra("meetingId", meetingId);
                    intent.putExtra("role", role);
                    if (role.equals("P")){
                        intent.putExtra("status", "Chat with doctor");
                    } else if (role.equals("D")){
                        intent.putExtra("status", "Chat with patient");
                    } else {
                        intent.putExtra("status", "Back to home");
                    }
                    itemView.getContext().startActivity(intent);
                }
            }
        });
    }
}
