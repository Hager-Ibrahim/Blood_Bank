package com.example.myapplication.view.fragments.homeCycle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ArticlesAdapter;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.data.model.Article;
import com.example.myapplication.data.model.general.BasicList;
import com.example.myapplication.data.model.homeCycle.DonationPageData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesFragment extends Fragment {

    RecyclerView articlesRecycler;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_articles, container, false);

        setArticlesRecycler(view);
        getAllPosts();

        return view;
    }

    private void setArticlesRecycler(View view){
        List<Article> at = new ArrayList<>();
        at.add(new Article("Blood Types", R.drawable.test));
        articlesRecycler = view.findViewById(R.id.articles_recycler);
        articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesRecycler.setAdapter(new ArticlesAdapter(at, getContext()));
    }

    private void getAllPosts(){
        ApiClient.getClient().create(ApiInterface.class).getPosts(
                "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", 1)
                .enqueue(new Callback<BasicList<DonationPageData>>() {
                    @Override
                    public void onResponse(Call<BasicList<DonationPageData>> call, Response<BasicList<DonationPageData>> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(), String.valueOf(response.body().getStatus()),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicList<DonationPageData>> call, Throwable t) {
                        Log.v("888888", t.getMessage());
                    }
                });
    }
}
