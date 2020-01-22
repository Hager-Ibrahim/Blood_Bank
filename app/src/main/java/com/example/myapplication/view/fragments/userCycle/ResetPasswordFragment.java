package com.example.myapplication.view.fragments.userCycle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.userCycle.ResetPassword;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.utilities.MostUsedMethods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordFragment extends Fragment {

    Button sendButton;
    EditText resetPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_reset_password, container, false);

        sendButton = view.findViewById(R.id.login_button);
        resetPassword = view.findViewById(R.id.reset_password_number_id);

        resetPassword();
        return view;
    }

    private void resetPassword(){

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ApiClient.getClient().create(ApiInterface.class).resetPassword(
                       resetPassword.getText().toString().trim())
                       .enqueue(new Callback<BasicObject<ResetPassword>>() {
                           @Override
                           public void onResponse(Call<BasicObject<ResetPassword>> call, Response<BasicObject<ResetPassword>> response) {
                               if(response.body().getStatus() == 1){
                                   MostUsedMethods.setToastMessage(getContext(), "1");
                               }
                               else {
                                   MostUsedMethods.setToastMessage(getContext(), response.body().getMsg());

                               }
                           }

                           @Override
                           public void onFailure(Call<BasicObject<ResetPassword>> call, Throwable t) {
                               MostUsedMethods.setToastMessage(getContext(), t.getMessage());

                           }
                       });
            }
        });
    }


}
