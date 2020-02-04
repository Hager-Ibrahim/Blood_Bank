package com.example.myapplication.view.fragments.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SpinnerCustomAdapter;
import com.example.myapplication.data.model.homeCycle.PageData;
import com.example.myapplication.data.model.homeCycle.Post;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.utilities.MostUsedMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.myapplication.data.retrofit.ApiClient.getClient;

public class PostsFragment extends Fragment {

    View view;
    SpinnerCustomAdapter categoryAdapter;
    @BindView(R.id.posts_recycler)
    RecyclerView postsRecycler;
    @BindView(R.id.posts_category_spinner)
    Spinner spinner;
    @BindView(R.id.articles_search_view)
    SearchView articlesSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posts, container, false);
        ButterKnife.bind(this, view);
        categoryAdapter = new SpinnerCustomAdapter(getContext());

        getAllPosts();
        setSpinner();
        return view;
    }

    private void setSpinner() {
        MostUsedMethods.getSpinnerData(
                getClient().create(ApiInterface.class).getCategories(),
                categoryAdapter,
                getActivity().getResources().getString(R.string.category),
                spinner);

        MostUsedMethods.setSpinnerListener(spinner,
                null,
                null,
                getContext(),
                "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",
                1,
                articlesSearchView.getQuery().toString(),
                postsRecycler);
    }

    private void getAllPosts() {
        MostUsedMethods<PageData<List<Post>>> usedMethods = new MostUsedMethods<>();
        usedMethods.getBasicObjectData(getClient().create(ApiInterface.class).getPosts(
                "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",
                1
                , null,
                0)
                , getContext()
                , null
                , 0
                , null
                , postsRecycler,
                null);
    }


}
