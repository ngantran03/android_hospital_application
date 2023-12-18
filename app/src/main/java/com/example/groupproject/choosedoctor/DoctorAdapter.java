// This set value to the adapter view

package com.example.groupproject.choosedoctor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.R;
import com.example.groupproject.database.EmployeeProfile;
import com.example.groupproject.database.GetInformation;
import com.example.groupproject.datamodel.AppointmentDataModel;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorHolder> {
    private List<EmployeeProfile> employeeList;
    private String patientUserId;
    public DoctorAdapter(List<EmployeeProfile> employeeList, String patientUserId){
        this.employeeList = employeeList;
        this.patientUserId = patientUserId;
    }

    // Constructor and other methods
    @Override
    public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create and return a new instance of your ViewHolder here
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_layout, parent, false);

        return new DoctorHolder(itemView, this.patientUserId);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorHolder holder, int position) {
        // Bind data to the template views based on the position in the list
        EmployeeProfile employee = employeeList.get(position);

        // For example:
        String userid = employee.getUserIdFromProfile();
        holder.userid.setText(userid);
        holder.doctorname.setText(GetInformation.getLastName(userid) + " " + GetInformation.getFirstName(userid));
        holder.specialty.setText(employee.getSpecialtyFromProfile());
        int review = employee.getReview();
        if (review == 0){
            holder.rating.setVisibility(View.INVISIBLE);
            holder.ratingText.setVisibility(View.INVISIBLE);
            holder.review.setVisibility(View.INVISIBLE);
            holder.reviewText.setVisibility(View.INVISIBLE);
        } else {
            holder.rating.setText(String.valueOf(employee.getRating()));
            holder.review.setText(String.valueOf(employee.getReview()));
        }

    }

    @Override
    public int getItemCount() {
        // Return the number of items in the data list
        return employeeList.size();
    }
}
