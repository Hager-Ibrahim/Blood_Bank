package com.example.myapplication.view.fragments.homeCycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.utilities.MostUsedMethods;

public class DonationOrderFragment extends Fragment {

    Spinner bloodTypeSpinner , citySpinner , governorteSpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_donation_order, container, false);


        bloodTypeSpinner = view.findViewById(R.id.add_donation_blood_spinner);
        citySpinner = view.findViewById(R.id.add_donation_city_spinner);
        governorteSpinner = view.findViewById(R.id.add_donation_governorate_spinner);


        return view;
    }

}
