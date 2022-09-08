package com.example.newsapp;

import com.example.newsapp.Models.Article;

import java.util.List;

public interface OnFetchDataListener {
    void onFetchData(List<Article> list, String message);

    void onError(String message);
}
