package com.example.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.general.BasicObject;
import com.example.myapplication.data.model.homeCycle.Post;
import com.example.myapplication.data.retrofit.ApiClient;
import com.example.myapplication.data.retrofit.ApiInterface;
import com.example.myapplication.data.sharedPreference.AppPreference;
import com.example.myapplication.utilities.MostUsedMethods;
import com.example.myapplication.view.fragments.homeCycle.ShowOneDonationFragment;
import com.example.myapplication.view.fragments.homeCycle.ShowPostFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.utilities.Constants.KEY_DONATION_ID;
import static com.example.myapplication.utilities.Constants.KEY_POST_ID;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ArticlesHolder> {

    List<Post> posts;
    Context context ;

    public PostsAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticlesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent, false);
        return new ArticlesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticlesHolder holder, int position) {
        holder.articleTitle.setText(posts.get(position).getTitle());

        Picasso.get()
                .load(posts.get(position).getThumbnailFullPath())
                .into(holder.articleImage);

        if(posts.get(position).getIsFavourite()){
            holder.likeButton.setVisibility(View.VISIBLE);
            holder.unlikeButton.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!posts.get(position).getIsFavourite()){
                    ApiClient.getClient().create(ApiInterface.class).addFavourites(
                            posts.get(position).getId()).enqueue(new Callback<BasicObject>() {
                        @Override
                        public void onResponse(Call<BasicObject> call, Response<BasicObject> response) {
                            if(response.isSuccessful()){
                                MostUsedMethods.setToastMessage(context, String.valueOf(response.body().getMsg()));
                            }
                        }

                        @Override
                        public void onFailure(Call<BasicObject> call, Throwable t) {
                            MostUsedMethods.setToastMessage(context, t.getMessage());
                        }
                    });
                }


                MostUsedMethods.replaceFragment(
                        ((FragmentActivity)context).getSupportFragmentManager(),
                        R.id.home_fragment_container,
                        new ShowPostFragment());
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ArticlesHolder extends RecyclerView.ViewHolder{

        ImageView articleImage;
        TextView articleTitle ;
        View likeButton , unlikeButton;

        public ArticlesHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.item_article_image);
            articleTitle = itemView.findViewById(R.id.item_article_topic);
            likeButton = itemView.findViewById(R.id.item_article_like);
            unlikeButton = itemView.findViewById(R.id.item_article_unlike);
        }
    }
}
