package com.example.myapplication.view.fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

public class LoginFragment extends Fragment {

    TextView goRegisterButton , forgetPassword;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        goRegisterButton = view.findViewById(R.id.login_register_here);
        forgetPassword = view.findViewById(R.id.login_forget_password);

        goToRegisterFragment();
        goToSendCodeFragment();
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,
                        new VerificationCodeFragment()).commit();
            }
        });
    }
}
