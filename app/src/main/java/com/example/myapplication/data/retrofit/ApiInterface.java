package com.example.myapplication.data.retrofit;

import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.homeCycle.DonationInfo;
import com.example.myapplication.data.model.homeCycle.PageData;
import com.example.myapplication.data.model.general.GeneralResponse;
import com.example.myapplication.data.model.homeCycle.Post;
import com.example.myapplication.data.model.userCycle.ResetPassword;
import com.example.myapplication.data.model.userCycle.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.myapplication.utilities.Constants.METHOD_FAVOURITES;
import static com.example.myapplication.utilities.Constants.METHOD_GET_BLOOD_TYPES;
import static com.example.myapplication.utilities.Constants.METHOD_GET_CATEGORIES;
import static com.example.myapplication.utilities.Constants.METHOD_GET_DONATION;
import static com.example.myapplication.utilities.Constants.METHOD_GET_GOVERNORATES_LIST;
import static com.example.myapplication.utilities.Constants.METHOD_GET_CITIES;
import static com.example.myapplication.utilities.Constants.METHOD_GET_POSTS;
import static com.example.myapplication.utilities.Constants.METHOD_POST_DETAILDS;
import static com.example.myapplication.utilities.Constants.METHOD_REQUEST_DONATION;
import static com.example.myapplication.utilities.Constants.METHOD_RESET_PASSWORD;
import static com.example.myapplication.utilities.Constants.METHOD_SHOW_DONATION;
import static com.example.myapplication.utilities.Constants.METHOD_SIGN_IN;
import static com.example.myapplication.utilities.Constants.METHOD_SIGN_UP;

public interface ApiInterface {

    @GET(METHOD_GET_GOVERNORATES_LIST)
    Call<BasicObject<List<GeneralResponse>>> getGovernorates();

    @GET(METHOD_GET_CITIES)
    Call<BasicObject<List<GeneralResponse>>> getCities(@Query("governorate_id") int id);

    @GET(METHOD_GET_BLOOD_TYPES)
    Call<BasicObject<List<GeneralResponse>>> getBloodTypeList();

    @GET(METHOD_GET_CATEGORIES)
    Call<BasicObject<List<GeneralResponse>>> getCategories();

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
    Call<BasicObject<User>> Login(
            @Field("phone") String phone,
            @Field("password") String password);

    @GET(METHOD_GET_POSTS)
    Call<BasicObject<PageData<List<Post>>>> getPosts(
            @Query("api_token") String api,
            @Query("page") int page,
            @Query("keyword") String keyword,
            @Query("category_id") int id);

    @FormUrlEncoded
    @POST(METHOD_RESET_PASSWORD)
    Call<BasicObject<ResetPassword>> resetPassword(@Field("phone") String phone);

    @GET(METHOD_GET_DONATION)
    Call<BasicObject<PageData<List<DonationInfo>>>> donationListFilter(
                                               @Header ("Accept") String accept,
                                               @Query("api_token") String apiToken,
                                               @Query("blood_type_id") int bloodId,
                                               @Query("governorate_id") int governorateId,
                                               @Query("page") int page);
    @GET(METHOD_GET_DONATION)
    Call<BasicObject<PageData<List<DonationInfo>>>> donationList(
            @Header ("Accept") String accept,
            @Query("api_token") String apiToken,
            @Query("page") int page);

    @FormUrlEncoded
    @POST(METHOD_FAVOURITES)
    Call<BasicObject> addFavourites(@Field("post_id") int postId);

    @GET(METHOD_SHOW_DONATION)
    Call<BasicObject<DonationInfo>> displayOneDonation(@Query("api_token") String apiToken,
                                                       @Query("donation_id") int donationId);


    @GET(METHOD_POST_DETAILDS)
    Call<BasicObject<Post>> getPostDetails(@Query("api_token") String apiToken,
                          @Query("post_id") int postId,
                          @Query("page") int page);

}

