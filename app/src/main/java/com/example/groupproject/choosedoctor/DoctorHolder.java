package com.example.groupproject.choosedoctor;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;
import com.example.groupproject.bookappointment.BookAppointment_DoctorDetail;
import com.example.groupproject.bookappointment.BookAppointment_Main;

public class DoctorHolder extends RecyclerView.ViewHolder {
    public TextView doctorname, specialty, ratingText, rating, review, reviewText, userid;
    public Button bookAppointment;
    public DoctorHolder(@NonNull View itemView, String patientUserId) {
        super(itemView);
        // Initialize your views here
        // For example:
        doctorname = itemView.findViewById(R.id.doctorname);
        specialty = itemView.findViewById(R.id.specialty);
        ratingText = itemView.findViewById(R.id.ratingText);
        rating = itemView.findViewById(R.id.rating);
        review = itemView.findViewById(R.id.review);
        reviewText = itemView.findViewById(R.id.reviewText);
        userid = itemView.findViewById(R.id.userid);
        bookAppointment = itemView.findViewById(R.id.bookAppointment);

        bookAppointment.setOnClickListener(new View.OnClickListener() {
            Context context = itemView.getContext();
            @Override
            public void onClick(View v) {
                // Use itemView.getContext() to obtain the context
                Context context = itemView.getContext();

                // Use Router class to create the Intent
                Intent intent = new Intent(context, BookAppointment_DoctorDetail.class);
                String doctorId = userid.getText().toString();
                intent.putExtra("doctorUserId", doctorId);
                intent.putExtra("patientUserId", patientUserId);
                // Start the activity using the context from the itemView
                context.startActivity(intent);
            }
        });
    }
}
