package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.general.GeneralResponse;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCustomAdapter extends BaseAdapter {


    private Context context;
    private List<GeneralResponse> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflater;
    public int selectedId = 0;

    public SpinnerCustomAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GeneralResponse> generalResponseDataList, String hint) {
        generalResponseDataList.add(0,new GeneralResponse(0, hint));
        this.generalResponseDataList = generalResponseDataList;
    }

    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner, null);

        TextView names =  view.findViewById(R.id.item_spinner_text);

        names.setText(generalResponseDataList.get(i).getName());
        selectedId = generalResponseDataList.get(i).getId();

        return view;
    }

}
