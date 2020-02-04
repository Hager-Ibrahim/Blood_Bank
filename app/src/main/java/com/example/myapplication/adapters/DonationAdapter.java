package com.example.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.myapplication.R;
import com.example.myapplication.data.model.homeCycle.DonationInfo;
import com.example.myapplication.data.sharedPreference.AppPreference;
import com.example.myapplication.utilities.MostUsedMethods;
import com.example.myapplication.view.fragments.homeCycle.ShowOneDonationFragment;

import java.util.List;

import static com.example.myapplication.utilities.Constants.KEY_DONATION_ID;


public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationHolder> {

    private Context context;
    private List<DonationInfo> donationList;
    Activity activity;

    public DonationAdapter(Context context, List<DonationInfo> donationList) {
        this.context = context;
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donation, parent, false);
        return new DonationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationHolder holder, int position) {

        String patient = context.getResources().getString(R.string.patient_name) + ": " +
                donationList.get(position).getPatientName();
        holder.donationPatient.setText(String.format(patient));

        String hospital = context.getResources().getString(R.string.hospital) + ": " +
                donationList.get(position).getHospitalName();
        holder.donationHospital.setText(hospital);

        String city = context.getResources().getString(R.string.city) + ": " +
                donationList.get(position).getPatientName();
        holder.donationCity.setText(city);


        holder.donationBloodType.setText(donationList.get(position).getBloodType().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppPreference.SaveInt((Activity) context,KEY_DONATION_ID,donationList.get(position).getId());
                MostUsedMethods.replaceFragment(
                        ((FragmentActivity)context).getSupportFragmentManager(),
                        R.id.home_fragment_container,
                        new ShowOneDonationFragment());
            }
        });

        //viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(donationListFilter.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    public class DonationHolder extends RecyclerView.ViewHolder {

        TextView donationPatient, donationHospital, donationCity, donationBloodType;
        SwipeRevealLayout swipeRevealLayout;

        public DonationHolder(@NonNull View itemView) {
            super(itemView);
            donationPatient = itemView.findViewById(R.id.donation_patient_name);
            donationHospital = itemView.findViewById(R.id.donation_hospital_name);
            donationCity = itemView.findViewById(R.id.donation_city_name);
            donationBloodType = itemView.findViewById(R.id.donation_blood_type);
        }
    }

}
