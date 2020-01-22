package com.example.myapplication.view.fragments.homeCycle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DonationAdapter;
import com.example.myapplication.data.model.homeCycle.DonationInfo;
import com.example.myapplication.utilities.MostUsedMethods;

import java.util.ArrayList;

public class DonationFragment extends Fragment {

    RecyclerView donationRecycler;
    Spinner bloodSpinner, governorateSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_donation, container, false);

        bloodSpinner = view.findViewById(R.id.donation_blood_type_spinner);
        governorateSpinner = view.findViewById(R.id.donation_governorate_spinner);

        setDonationRecycler(view);
        setSpinners();
        addDonation(view);

        return view;
    }

    private void addDonation(View view){
        view.findViewById(R.id.donation_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container,
                        new DonationOrderFragment()).commit();

            }
        });
    }

    private void setSpinners(){


    }
    private void setDonationRecycler(View view){

        ArrayList<DonationInfo> donations = new ArrayList<>();
        donations.add(new DonationInfo("اسم الحالة", "مستشفى " ,"المدينة", "B+"));
        donations.add(new DonationInfo("اسم الحالة", "مستشفى " ,"المدينة", "A+"));
        donations.add(new DonationInfo("اسم الحالة", "مستشفى " ,"المدينة", "O+"));
        donations.add(new DonationInfo("اسم الحالة", "مستشفى " ,"المدينة", "A+"));

        donationRecycler = view.findViewById(R.id.donation_recycler);
        donationRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        donationRecycler.setAdapter(new DonationAdapter(getContext(),donations));
    }
}
