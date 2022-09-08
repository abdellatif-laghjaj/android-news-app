package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.newsapp.Models.Article;
import com.example.newsapp.Models.NewsApiResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getArticle(listener, "general", null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Article> list, String message) {
            showNews(list);
            progressDialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<Article> list) {
        recyclerView = findViewById(R.id.recycler_main);
        articleAdapter = new ArticleAdapter(this, list, this);
        articleAdapter.setHasStableIds(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(articleAdapter);
    }

    @Override
    public void OnNewsClick(Article article) {
        startActivity(new Intent(this, DetailsActivity.class)
                .putExtra("data", article));
    }
}