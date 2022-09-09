package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newsapp.Models.Article;
import com.example.newsapp.Models.NewsApiResponse;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    ProgressDialog progressDialog;
    SearchView searchView;
    TextView text_view_1, text_view_2, text_view_3, text_view_4, text_view_5, text_view_6, text_view_7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProgressDialog("Loading articles...");

        RequestManager manager = new RequestManager(this);
        manager.getArticle(listener, "technology", null);

        text_view_1 = findViewById(R.id.text_view_1);
        text_view_2 = findViewById(R.id.text_view_2);
        text_view_3 = findViewById(R.id.text_view_3);
        text_view_4 = findViewById(R.id.text_view_4);
        text_view_5 = findViewById(R.id.text_view_5);
        text_view_6 = findViewById(R.id.text_view_6);
        text_view_7 = findViewById(R.id.text_view_7);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showProgressDialog("Searching for " + query + "...");
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getArticle(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        text_view_1.setOnClickListener(this);
        text_view_2.setOnClickListener(this);
        text_view_3.setOnClickListener(this);
        text_view_4.setOnClickListener(this);
        text_view_5.setOnClickListener(this);
        text_view_6.setOnClickListener(this);
        text_view_7.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        TextView textView = (TextView) view;
        String category = textView.getText().toString().toLowerCase(Locale.ROOT);
        showProgressDialog("Loading articles of " + category + " category...");
        RequestManager manager = new RequestManager(this);
        manager.getArticle(listener, category, null);
    }

    public void showProgressDialog(String title) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.show();
    }
}