package com.example.myapplication.utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.DonationAdapter;
import com.example.myapplication.adapters.PostsAdapter;
import com.example.myapplication.adapters.SpinnerCustomAdapter;
import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.general.GeneralResponse;
import com.example.myapplication.data.model.homeCycle.DonationInfo;
import com.example.myapplication.data.model.homeCycle.PageData;
import com.example.myapplication.data.model.homeCycle.Post;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.data.sharedPreference.AppPreference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.data.retrofit.ApiClient.getClient;
import static com.example.myapplication.utilities.Constants.KEY_DONATION_BLOOD_ID;
import static com.example.myapplication.utilities.Constants.KEY_DONATION_GOVERNORATE_ID;
import static com.example.myapplication.utilities.Constants.KEY_GOVERNORATE_NAME;
import static com.example.myapplication.utilities.Constants.KEY_IS_FAVOURITE;
import static com.example.myapplication.utilities.Constants.KEY_POST_ID;
import static com.example.myapplication.utilities.Constants.KEY_POST_PAGE;

public class MostUsedMethods<T> {


    public static void getSpinnerData(Call<BasicObject<List<GeneralResponse>>> generalResponseCall,
                                      final SpinnerCustomAdapter spinnerAdapter,
                                      final String hint,
                                      final Spinner spinner) {
        generalResponseCall.enqueue(new Callback<BasicObject<List<GeneralResponse>>>() {
            @Override
            public void onResponse(Call<BasicObject<List<GeneralResponse>>> call, Response<BasicObject<List<GeneralResponse>>> response) {
                if (response.body().getStatus() == 1) {
                    spinnerAdapter.setData(response.body().getData(), hint);
                    spinner.setAdapter(spinnerAdapter);
                } else {
                    Log.v("3333", response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BasicObject<List<GeneralResponse>>> call, Throwable t) {
                Log.v("888", t.getMessage());
            }
        });
    }



    public void getBasicObjectData(Call<BasicObject<T>> userCall,
                                   Context context,
                                   FragmentManager fragmentManager,
                                   int containerId,
                                   Fragment fragment,
                                   RecyclerView recyclerView,
                                   ArrayList<TextView> textViews) {

        userCall.enqueue(new Callback<BasicObject<T>>() {
            @Override
            public void onResponse(Call<BasicObject<T>> call, Response<BasicObject<T>> response) {

                if (response.body().getStatus() == 1) {
                    if (fragment != null) {
                        replaceFragment(fragmentManager, containerId, fragment);

                    } else if (recyclerView !=null && recyclerView.getId() == R.id.posts_recycler) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        PageData<List<Post>> posts = (PageData<List<Post>>) response.body().getData();
                        recyclerView.setAdapter(new PostsAdapter(posts.getData(), context));

                    } else if (recyclerView !=null && recyclerView.getId() == R.id.donation_recycler) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        PageData<List<DonationInfo>> donations = (PageData<List<DonationInfo>>) response.body().getData();
                        recyclerView.setAdapter(new DonationAdapter(context, donations.getData()));
                    }

                    else if(textViews != null && textViews.size() == 8){
                        DonationInfo donationInfo  = (DonationInfo) response.body().getData();
                        textViews.get(0).setText(donationInfo.getPatientName());
                        textViews.get(1).setText(donationInfo.getPatientAge());
                        textViews.get(2).setText(donationInfo.getBloodType().getName());
                        textViews.get(3).setText(donationInfo.getBagsNum());
                        textViews.get(4).setText(donationInfo.getHospitalName());
                        textViews.get(5).setText(donationInfo.getHospitalAddress());
                        textViews.get(6).setText(donationInfo.getPhone());
                        textViews.get(7).setText(context.getResources().getString(R.string.donation_order) +
                                ": " + donationInfo.getPatientName());
                        AppPreference.SaveData((Activity) context,KEY_GOVERNORATE_NAME,donationInfo.getCity().getName());
                    }
                    else if(textViews != null ){
                        Post post = (Post) response.body().getData();
                        textViews.get(0).setText(post.getTitle());
                        textViews.get(1).setText(post.getContent());
                        textViews.get(2).setText(post.getTitle());

                        AppPreference.SaveInt((Activity) context,KEY_POST_ID,post.getId());
                        AppPreference.SaveBoolean((Activity) context,KEY_IS_FAVOURITE,post.getIsFavourite());

                    }

                } else {
                    setToastMessage(context, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BasicObject<T>> call, Throwable t) {
                setToastMessage(context, t.getMessage());

            }
        });
    }


    public static void setSpinnerListener(Spinner spinner,
                                          final Spinner citySpinner,
                                          final SpinnerCustomAdapter spinnerAdapter,
                                          final Context context,
                                          String api,
                                          int page,
                                          String keyword,
                                          RecyclerView recyclerView) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (spinner.getId()) {
                    case R.id.register_governorate_spinner:
                        getSpinnerData(
                                getClient().create(ApiInterface.class).getCities(i),
                                spinnerAdapter,
                                context.getResources().getString(R.string.choose_city),
                                citySpinner
                        );
                        break;
                    case R.id.posts_category_spinner:
                        MostUsedMethods<PageData<List<Post>>> usedMethods = new MostUsedMethods<>();
                        usedMethods.getBasicObjectData(ApiClient.getClient().create(ApiInterface.class).getPosts(
                                api,
                                page,
                                keyword,
                                i), context, null, 0, null, recyclerView, null);
                        break;
                    case R.id.donation_blood_type_spinner:
                        AppPreference.SaveInt((Activity) context, KEY_DONATION_BLOOD_ID, i);
                        int goverId = AppPreference.LoadInt((Activity) context, KEY_DONATION_GOVERNORATE_ID);
                        setDonationRecycler(context, i, goverId, recyclerView);

                        break;
                    case R.id.donation_governorate_spinner:
                        AppPreference.SaveInt((Activity) context, KEY_DONATION_GOVERNORATE_ID, i);
                        int bloodId = AppPreference.LoadInt((Activity) context, KEY_DONATION_BLOOD_ID);
                        setDonationRecycler(context, bloodId, i, recyclerView);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public static void setToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void setDatePickerDialog(Context context, EditText dateEditText) {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String date = i + "-" + i1 + 1 + "-" + i2;
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
            Fragment fragment) {
        fragmentManager.beginTransaction().replace(containerId,
                fragment).commit();
    }

    public static void setDonationRecycler(Context context,
                                           int bloodId,
                                           int governorateId,
                                           RecyclerView donationRecycler) {

        MostUsedMethods<PageData<List<DonationInfo>>> usedMethods = new MostUsedMethods<>();
        usedMethods.getBasicObjectData(ApiClient.getClient().create(ApiInterface.class).donationListFilter(
                "application/json",
                "W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",
                bloodId,
                governorateId,
                1),
                context
                , null
                , 0
                , null
                , donationRecycler,
                null);
    }

}
