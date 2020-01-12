package com.example.myapplication.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DonationAdapter extends RecyclerView.Adapter {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DonationHolder extends RecyclerView.ViewHolder{

        TextView donationText1 , donationText2 ,donationText3;
        public DonationHolder(@NonNull View itemView) {
            super(itemView);
            donationText1 = itemView.findViewById(R.id.donation_text1);
            donationText2 = itemView.findViewById(R.id.donation_text2);
            donationText3 = itemView.findViewById(R.id.donation_text3);
        }
    }



}
