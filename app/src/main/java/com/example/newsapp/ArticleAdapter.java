package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<Article> articles;
    private SelectListener selectListener;

    public ArticleAdapter(Context context, List<Article> articles, SelectListener selectListener) {
        this.context = context;
        this.articles = articles;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_title.setText(articles.get(position).getTitle());
        holder.text_source.setText(articles.get(position).getSource().getName());

        if (articles.get(position).getUrlToImage() != null) {
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.img_headline);
        }

        holder.cardView.setOnClickListener(v ->{
            selectListener.OnNewsClick(articles.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
