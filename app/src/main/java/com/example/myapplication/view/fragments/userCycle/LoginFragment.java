package com.example.myapplication.view.fragments.userCycle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.userCycle.User;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.utilities.MostUsedMethods;
import com.example.myapplication.view.fragments.homeCycle.HomeFragment;

public class LoginFragment extends Fragment {

    TextView goRegisterButton , forgetPassword;
    EditText numberEditText, passwordEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        goRegisterButton = view.findViewById(R.id.login_register_here);
        forgetPassword = view.findViewById(R.id.login_forget_password);
        numberEditText = view.findViewById(R.id.login_number);
        passwordEditText = view.findViewById(R.id.login_password);

        goToRegisterFragment();
        goToSendCodeFragment();
        login(view);
        return view;
    }

    private void goToRegisterFragment(){
        goRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,
                        new RegisterFragment()).commit();
            }
        });
    }

    private void goToSendCodeFragment(){
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostUsedMethods.replaceFragment(
                        getActivity().getSupportFragmentManager(),
                        R.id.user_fragment_container,
                        new ResetPasswordFragment()
                );
            }
        });
    }

    private void login(View view){

        view.findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostUsedMethods<User> usedMethods = new MostUsedMethods<>();
                usedMethods.getBasicObjectData(ApiClient.getClient().create(ApiInterface.class).Login(
                    numberEditText.getText().toString().trim(),
                        passwordEditText.getText().toString().trim()
                ),
                        getContext(),
                        getActivity().getSupportFragmentManager(),
                        R.id.user_fragment_container,
                        new HomeFragment());
            }
        });

    }
}
