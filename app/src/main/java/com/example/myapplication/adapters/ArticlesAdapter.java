package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.models.Article;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder> {

    List<Article> articles;
    Context context ;

    public ArticlesAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticlesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aricles,parent, false);
        return new ArticlesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticlesHolder holder, int position) {
        holder.articleImage.setImageResource(articles.get(position).getDrawable());
        holder.articleTitle.setText(articles.get(position).getArticleTest());

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeButton.setVisibility(View.INVISIBLE);
                holder.unlikeButton.setVisibility(View.VISIBLE);
            }
        });

        holder.unlikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.likeButton.setVisibility(View.VISIBLE);
                holder.unlikeButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
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
