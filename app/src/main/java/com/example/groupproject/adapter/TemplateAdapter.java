// This set value to the adapter view

package com.example.groupproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;
import com.example.groupproject.bookappointment.BookAppointment_Result;
import com.example.groupproject.database.Appointment;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.database.Profile;
import com.example.groupproject.datamodel.AppointmentDataModel;

import java.util.List;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateViewHolder> {
    private List<Appointment> dataList;
    private String role;
    public TemplateAdapter(List<Appointment> dataList, String role){
        this.dataList = dataList;
        this.role = role;
    }

    // Constructor and other methods
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create and return a new instance of your ViewHolder here
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_layout, parent, false);

        return new TemplateViewHolder(dataList, itemView, this.role);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        // Bind data to the template views based on the position in the list
        Appointment appointment = dataList.get(position);
        // For example:
        holder.appointmentDateInput.setText(appointment.getDate());
        holder.appointmentTimeInput.setText(appointment.getTime());
        GetInformation.initializeDatabase(holder.itemView.getContext());
        String doctorId = appointment.getDoctorId();
        String patientId = appointment.getPatientId();
        if (role.equals("P")) {
            holder.doctorNameInput.setText(GetInformation.getLastName(doctorId) + " " + GetInformation.getFirstName(doctorId));
        } else {
            holder.doctorNameInput.setText(GetInformation.getLastName(patientId) + " " + GetInformation.getFirstName(patientId));
        }
        holder.doctorSpecInput.setText(GetInformation.getSpecialty(doctorId));

//        holder.click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int meetingId = appointment.getMeetingId();
//                Intent intent = new Intent(holder.click.getContext(), BookAppointment_Result.class);
//                intent.putExtra("meetingId", meetingId);
//                if (role.equals("P")){
//                    intent.putExtra("status", "Chat with doctor");
//                } else {
//                    intent.putExtra("status", "Chat with patient");
//                }
//                holder.click.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the data list
        return dataList.size();
    }
}
