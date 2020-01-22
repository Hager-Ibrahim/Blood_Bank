package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.homeCycle.DonationInfo;

import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationHolder> {

    private Context context ;
    private List<DonationInfo> donationList;

    public DonationAdapter(Context context, List<DonationInfo> donationList) {
        this.context = context;
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donation,parent,false);
        return new DonationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationHolder holder, int position) {
        holder.donationPatient.setText(donationList.get(position).getPatientName());
        holder.donationHospital.setText(donationList.get(position).getHospitalName());
        holder.donationCity.setText(donationList.get(position).getPatientName());
        holder.donationBloodType.setText("A+");

    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    public class DonationHolder extends RecyclerView.ViewHolder{

        TextView donationPatient, donationHospital, donationCity , donationBloodType;

        public DonationHolder(@NonNull View itemView) {
            super(itemView);
            donationPatient = itemView.findViewById(R.id.donation_patient_name);
            donationHospital = itemView.findViewById(R.id.donation_hospital_name);
            donationCity = itemView.findViewById(R.id.donation_city_name);
            donationBloodType = itemView.findViewById(R.id.donation_blood_type);
        }
    }



}
