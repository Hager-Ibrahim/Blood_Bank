package com.example.myapplication.view.fragments.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SpinnerCustomAdapter;
import com.example.myapplication.data.model.userCycle.User;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.utilities.MostUsedMethods;
import com.example.myapplication.view.fragments.homeCycle.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.myapplication.data.retrofit.ApiClient.getClient;

public class RegisterFragment extends Fragment {

    SpinnerCustomAdapter bloodTypeAdapter, governorateAdapter, cityAdapter;

    @BindView(R.id.register_blood_type_spinner)
    Spinner registerBloodTypeSpinner;
    @BindView(R.id.register_governorate_spinner)
    Spinner registerGovernorateSpinner;
    @BindView(R.id.register_city_spinner)
    Spinner registerCitySpinner;
    @BindView(R.id.register_user_name_edit_text)
    EditText userNameEditText;
    @BindView(R.id.register_email_edit_text)
    EditText emailEditText;
    @BindView(R.id.register_birth_date_edit_text)
    EditText birthDateEditText;
    @BindView(R.id.register_last_donation_date_edit_text)
    EditText lastDonationDateEditText;
    @BindView(R.id.register_number_edit_text)
    EditText numberEditText;
    @BindView(R.id.register_password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.register_confirm_password)
    EditText confirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        bloodTypeAdapter = new SpinnerCustomAdapter(getContext());
        governorateAdapter = new SpinnerCustomAdapter(getContext());
        cityAdapter = new SpinnerCustomAdapter(getContext());

        setSpinners();

        return view;
    }


    private void setSpinners() {
        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getBloodTypeList(),
                bloodTypeAdapter,
                getActivity().getResources().getString(R.string.choose_blood_type),
                registerBloodTypeSpinner);

        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getGovernorates(),
                governorateAdapter,
                getActivity().getResources().getString(R.string.choose_governorate),
                registerGovernorateSpinner
        );

        MostUsedMethods.setSpinnerListener(
                registerGovernorateSpinner,
                registerCitySpinner,
                cityAdapter,
                getActivity(),
                null,
                0,
                null,
                null);

    }

    @OnClick({R.id.register_birth_date_edit_text, R.id.register_last_donation_date_edit_text, R.id.register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_birth_date_edit_text:
                MostUsedMethods.setDatePickerDialog(getContext(), birthDateEditText);
                break;
            case R.id.register_last_donation_date_edit_text:
                MostUsedMethods.setDatePickerDialog(getContext(), lastDonationDateEditText);
                break;
            case R.id.register_button:

                MostUsedMethods<User> usedMethods = new MostUsedMethods<>();

                usedMethods.getBasicObjectData(getClient().create(ApiInterface.class).signUp(
                        userNameEditText.getText().toString().trim(),
                        emailEditText.getText().toString().trim(),
                        birthDateEditText.getText().toString().trim(),
                        String.valueOf(registerCitySpinner.getSelectedItemPosition()),
                        numberEditText.getText().toString().trim(),
                        lastDonationDateEditText.getText().toString().trim(),
                        passwordEditText.getText().toString().trim()
                        , confirmPassword.getText().toString().trim(),
                        String.valueOf(registerBloodTypeSpinner.getSelectedItemPosition())
                        ), getContext(),
                        getActivity().getSupportFragmentManager(),
                        R.id.user_fragment_container,
                        new HomeFragment(),
                        null,
                        null);
                break;
        }
    }
}
