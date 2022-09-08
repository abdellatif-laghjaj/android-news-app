package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.newsapp.Models.Article;

public class DetailsActivity extends AppCompatActivity {
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}