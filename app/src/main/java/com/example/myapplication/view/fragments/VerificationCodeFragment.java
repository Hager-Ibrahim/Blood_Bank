package com.example.myapplication.view.fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

public class VerificationCodeFragment extends Fragment {

    Button sendButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_verification_code, container, false);

        sendButton = view.findViewById(R.id.verification_code_send);
        openNewPasswordFragment();
        return view;
    }

    private void openNewPasswordFragment(){

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,
                        new NewPasswordFragment()).commit();
            }
        });
    }
}
