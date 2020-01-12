package com.example.myapplication.view.fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ArticlesAdapter;
import com.example.myapplication.data.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesFragment extends Fragment {

    RecyclerView articlesRecycler;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_articles, container, false);

        setArticlesRecycler(view);

        return view;
    }



    private void setArticlesRecycler(View view){
        List<Article> at = new ArrayList<>();
        at.add(new Article("الوقاية من الامراض", R.drawable.test));
        articlesRecycler = view.findViewById(R.id.articles_recycler);
        articlesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesRecycler.setAdapter(new ArticlesAdapter(at, getContext()));
    }
}
