package com.example.myapplication.data.retrofit;

import com.example.myapplication.data.model.general.BasicList;
import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.homeCycle.DonationPageData;
import com.example.myapplication.data.model.general.GeneralResponse;
import com.example.myapplication.data.model.userCycle.ResetPassword;
import com.example.myapplication.data.model.userCycle.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.myapplication.utilities.Constants.METHOD_GET_BLOOD_TYPES;
import static com.example.myapplication.utilities.Constants.METHOD_GET_GOVERNORATES_LIST;
import static com.example.myapplication.utilities.Constants.METHOD_GET_CITIES;
import static com.example.myapplication.utilities.Constants.METHOD_GET_POSTS;
import static com.example.myapplication.utilities.Constants.METHOD_RESET_PASSWORD;
import static com.example.myapplication.utilities.Constants.METHOD_SIGN_IN;
import static com.example.myapplication.utilities.Constants.METHOD_SIGN_UP;

public interface ApiInterface {

    @GET(METHOD_GET_GOVERNORATES_LIST)
    Call<BasicList<GeneralResponse>> getGovernorates();

    @GET(METHOD_GET_CITIES)
    Call<BasicList<GeneralResponse>> getCities(@Query("governorate_id") int id);

    @GET(METHOD_GET_BLOOD_TYPES)
    Call<BasicList<GeneralResponse>> getBloodTypeList();

    @POST(METHOD_SIGN_UP)
    @FormUrlEncoded
    Call<BasicObject<User>> signUp(@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("birth_date") String birthDate,
                                   @Field("city_id") String cityId,
                                   @Field("phone") String phone,
                                   @Field("donation_last_date") String date,
                                   @Field("password") String password,
                                   @Field("password_confirmation") String passwordConfirm,
                                   @Field("blood_type_id") String id);

    @FormUrlEncoded
    @POST(METHOD_SIGN_IN)
    Call<BasicObject<User>> Login(@Field("phone") String phone, @Field("password") String password);

    @GET(METHOD_GET_POSTS)
    Call<BasicList<DonationPageData>> getPosts(@Query("api_token") String api, @Query("page") int page);

    @FormUrlEncoded
    @POST(METHOD_RESET_PASSWORD)
    Call<BasicObject<ResetPassword>> resetPassword(@Field("phone") String phone);
}

