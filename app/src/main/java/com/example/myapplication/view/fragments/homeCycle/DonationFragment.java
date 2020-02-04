package com.example.myapplication.view.fragments.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SpinnerCustomAdapter;
import com.example.myapplication.data.model.homeCycle.DonationInfo;
import com.example.myapplication.data.model.homeCycle.PageData;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.utilities.MostUsedMethods;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.myapplication.data.retrofit.ApiClient.getClient;

public class DonationFragment extends Fragment {

    SpinnerCustomAdapter bloodTypeAdapter, governorateAdapter;

    @BindView(R.id.donation_governorate_spinner)
    Spinner donationGovernorateSpinner;
    @BindView(R.id.donation_blood_type_spinner)
    Spinner donationBloodTypeSpinner;
    @BindView(R.id.donation_recycler)
    RecyclerView donationRecycler;
    @BindView(R.id.donation_button)
    FloatingActionButton donationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation, container, false);

        ButterKnife.bind(this, view);
        bloodTypeAdapter = new SpinnerCustomAdapter(getContext());
        governorateAdapter = new SpinnerCustomAdapter(getContext());

        setDonationRecycler();
        setSpinners();
        return view;
    }


    private void setSpinners() {
        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getBloodTypeList(),
                bloodTypeAdapter,
                getActivity().getResources().getString(R.string.blood_type),
                donationBloodTypeSpinner);

        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getGovernorates(),
                governorateAdapter,
                getActivity().getResources().getString(R.string.governorate),
                donationGovernorateSpinner
        );
    }

    private void setDonationRecycler() {

        MostUsedMethods.setDonationRecycler(getContext(),0,0,donationRecycler);

        MostUsedMethods.setSpinnerListener(
                donationGovernorateSpinner,
                null,
                governorateAdapter,
                getContext(),
                null,
                0,
                null,
                donationRecycler
                );
        MostUsedMethods.setSpinnerListener(
                donationBloodTypeSpinner,
                null,
                bloodTypeAdapter,
                getContext(),
                null,
                0,
                null,
                donationRecycler
        );

    }

    @OnClick(R.id.donation_button)
    public void onViewClicked() {
        MostUsedMethods.replaceFragment(
                getActivity().getSupportFragmentManager(),
                R.id.home_fragment_container,
                new DonationOrderFragment());

    }
}
