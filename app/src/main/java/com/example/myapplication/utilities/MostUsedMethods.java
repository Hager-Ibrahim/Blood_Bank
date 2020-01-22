package com.example.myapplication.utilities;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SpinnerCustomAdapter;
import com.example.myapplication.data.model.general.BasicList;
import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.general.GeneralResponse;
import com.example.myapplication.data.model.userCycle.User;
import com.example.myapplication.data.retrofit.ApiInterface;

import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.data.retrofit.ApiClient.getClient;

public class MostUsedMethods<T>  {

    public static void getSpinnerData(Call<BasicList<GeneralResponse>> generalResponseCall,
                                      final SpinnerCustomAdapter spinnerAdapter,
                                      final String hint,
                                      final Spinner spinner) {
        generalResponseCall.enqueue(new Callback<BasicList<GeneralResponse>>() {
            @Override
            public void onResponse(Call<BasicList<GeneralResponse>> call, Response<BasicList<GeneralResponse>> response) {
                if(response.body().getStatus() == 1){
                    spinnerAdapter.setData(response.body().getData(),hint);
                    spinner.setAdapter(spinnerAdapter);
                }
                else{
                }
            }

            @Override
            public void onFailure(Call<BasicList<GeneralResponse>> call, Throwable t) {
            }
        });
    }


    public static void setSpinnerListener(Spinner spinner,
                                          final Spinner citySpinner,
                                          final SpinnerCustomAdapter spinnerAdapter,
                                          final Activity activity){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinner.getId()){
                    case R.id.register_governorate_spinner:
                        getSpinnerData(
                                getClient().create(ApiInterface.class).getCities(i),
                                spinnerAdapter,
                                activity.getResources().getString(R.string.choose_city),
                                citySpinner
                        );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public static void setToastMessage(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void setDatePickerDialog(Context context, EditText dateEditText){

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date =  i + "-" + i1+1 + "-" +i2 ;
                dateEditText.setText(date);
            }
        };

        new DatePickerDialog(
                context,
                dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    public static void replaceFragment(
            FragmentManager fragmentManager,
            int containerId,
            Fragment fragment){
        fragmentManager.beginTransaction().replace(containerId,
                fragment).commit();
    }

    public void getBasicObjectData(Call<BasicObject<T>> userCall ,
                                    Context context,
                                    FragmentManager fragmentManager,
                                    int containerId,
                                    Fragment fragment){

        userCall.enqueue(new Callback<BasicObject<T>>() {
            @Override
            public void onResponse(Call<BasicObject<T>> call, Response<BasicObject<T>> response) {

                if (response.body().getStatus() ==1) {
                    replaceFragment(fragmentManager, containerId, fragment);
                }
                else {
                    setToastMessage(context, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BasicObject<T>> call, Throwable t) {
                setToastMessage(context,t.getMessage());

            }
        });
    }

}
