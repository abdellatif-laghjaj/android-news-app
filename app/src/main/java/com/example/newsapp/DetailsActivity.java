package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.Article;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    Article article;
    TextView textTitle, textDescription, textContent, textAuthor, textPublishedAt;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        article = (Article) getIntent().getSerializableExtra("data");
        textTitle = findViewById(R.id.text_details_title);
        textDescription = findViewById(R.id.text_details_details);
        textContent = findViewById(R.id.text_details_content);
        textAuthor = findViewById(R.id.text_details_author);
        textPublishedAt = findViewById(R.id.text_details_time);
        imageView = findViewById(R.id.img_details_image);

        setData();
    }

    private void setData() {
        textTitle.setText(article.getTitle());
        textDescription.setText(article.getDescription());
        textContent.setText(article.getContent());
        textAuthor.setText(article.getAuthor());
        textPublishedAt.setText(article.getPublishedAt());
        Picasso.get().load(article.getUrlToImage()).into(imageView);
    }
}