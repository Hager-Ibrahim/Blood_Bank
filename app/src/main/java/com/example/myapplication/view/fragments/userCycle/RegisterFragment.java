package com.example.myapplication.view.fragments.userCycle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SpinnerCustomAdapter;
import com.example.myapplication.data.model.general.GeneralResponse;
import com.example.myapplication.data.model.userCycle.User;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.utilities.MostUsedMethods;
import com.example.myapplication.view.fragments.homeCycle.HomeFragment;

import static com.example.myapplication.data.retrofit.ApiClient.getClient;

public class RegisterFragment extends Fragment {

    Spinner bloodTypeSpinner , governorateSpinner, citySpinner ;
    SpinnerCustomAdapter bloodTypeAdapter, governorateAdapter, cityAdapter;
    EditText birthDate , lastDonationDate, userName, userEmail, userPhone, userPassword, userConfirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        bloodTypeSpinner = view.findViewById(R.id.register_blood_type_spinner);
        governorateSpinner = view.findViewById(R.id.register_governorate_spinner);
        citySpinner = view.findViewById(R.id.register_city_spinner);
        birthDate = view.findViewById(R.id.register_birth_date_edit_text);
        lastDonationDate =view.findViewById(R.id.register_last_donation_date_edit_text);
        userName =view.findViewById(R.id.register_user_name_edit_text);
        userEmail = view.findViewById(R.id.register_email_edit_text);
        userPhone = view.findViewById(R.id.register_number_edit_text);
        userPassword = view.findViewById(R.id.register_password_edit_text);
        userConfirmPassword = view.findViewById(R.id.register_confirm_password);

        bloodTypeAdapter = new SpinnerCustomAdapter(getContext());
        governorateAdapter = new SpinnerCustomAdapter(getContext());
        cityAdapter = new SpinnerCustomAdapter(getContext());


        setSpinners();
        setDates();


        signUp(view);
        return view;
    }


    private void signUp(View view){


        view.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MostUsedMethods<User> usedMethods = new MostUsedMethods<>();

                usedMethods.getBasicObjectData(getClient().create(ApiInterface.class).signUp(
                        userName.getText().toString().trim(),
                        userEmail.getText().toString().trim(),
                        birthDate.getText().toString().trim(),
                        String.valueOf(citySpinner.getSelectedItemPosition()),
                        userPhone.getText().toString().trim(),
                        lastDonationDate.getText().toString().trim(),
                        userPassword.getText().toString().trim()
                        ,userConfirmPassword.getText().toString().trim(),
                        String.valueOf(bloodTypeSpinner.getSelectedItemPosition())
                ), getContext(),
                        getActivity().getSupportFragmentManager(),
                        R.id.user_fragment_container,
                        new HomeFragment());

                Log.v("kkkk", String.valueOf(bloodTypeSpinner.getSelectedItemPosition() +
                      "-" +String.valueOf(citySpinner.getSelectedItemPosition())  ));
            }
        });
    }

    private void setSpinners(){
        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getBloodTypeList(),
                bloodTypeAdapter,
                getActivity().getResources().getString(R.string.choose_blood_type),
                bloodTypeSpinner);

        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getGovernorates(),
                governorateAdapter,
                getActivity().getResources().getString(R.string.choose_governorate) ,
                governorateSpinner
        );

        MostUsedMethods.setSpinnerListener(
                governorateSpinner,
                citySpinner,
                cityAdapter,
                getActivity());

    }

    private void setDates(){
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostUsedMethods.setDatePickerDialog(getContext(),birthDate);
            }
        });

        lastDonationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostUsedMethods.setDatePickerDialog(getContext(),lastDonationDate);
            }
        });
    }


}
