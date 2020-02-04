package com.example.myapplication.view.fragments.homeCycle;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.homeCycle.DonationInfo;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.data.sharedPreference.AppPreference;
import com.example.myapplication.utilities.MostUsedMethods;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.utilities.Constants.KEY_DONATION_ID;
import static com.example.myapplication.utilities.Constants.KEY_GOVERNORATE_NAME;

public class ShowOneDonationFragment extends Fragment implements OnMapReadyCallback {

    @BindView(R.id.one_donation_toolbar_text)
    TextView toolbarText;
    @BindView(R.id.show_donation_patient_name)
    TextView patientName;
    @BindView(R.id.show_donation_age)
    TextView patientAge;
    @BindView(R.id.show_donation_blood_type)
    TextView bloodType;
    @BindView(R.id.show_donation_number)
    TextView number;
    @BindView(R.id.show_donation_hospital)
    TextView hospital;
    @BindView(R.id.show_donation_address)
    TextView address;
    @BindView(R.id.show_donation_phone_number)
    TextView phoneNumber;

    SupportMapFragment supportMapFragment;
    ArrayList<TextView> textViews = new ArrayList<>();


    GoogleMap mMap;
    List<Address> addressList = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_one_donation, container, false);
        ButterKnife.bind(this,view);


        displayOneDonationInfo();

        return view;
    }

    private void displayOneDonationInfo(){
        textViews.add(patientName);
        textViews.add(patientAge);
        textViews.add(bloodType);
        textViews.add(number);
        textViews.add(hospital);
        textViews.add(address);
        textViews.add(phoneNumber);
        textViews.add(toolbarText);

        MostUsedMethods<DonationInfo> usedMethods = new MostUsedMethods();
        usedMethods.getBasicObjectData(
                ApiClient.getClient().create(ApiInterface.class).displayOneDonation(
                        "W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",
                        AppPreference.LoadInt(getActivity(), KEY_DONATION_ID)),
                getContext(),null,0,null,null,textViews);

        setUpMap();

    }

    private void setUpMap(){

        Geocoder geocoder = new Geocoder(getContext());
        try {
            addressList =geocoder.getFromLocationName(AppPreference.LoadData(getActivity(),KEY_GOVERNORATE_NAME),1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().
                findFragmentById(R.id.map_fragment2);
        supportMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap ;

        Address address = addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("k"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


    }
}
