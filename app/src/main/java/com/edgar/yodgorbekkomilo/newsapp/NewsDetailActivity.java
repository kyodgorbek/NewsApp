package com.edgar.yodgorbekkomilo.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by yodgorbekkomilov on 2/25/18.
 */

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        Article article = (Article) getIntent().getParcelableExtra("myDataKey");
        TextView textView = (TextView) findViewById(R.id.article_title);
        String articleTitle = article.getTitle();

        if (articleTitle != null) {
            textView.setText(articleTitle);
        }
        TextView textView1 = (TextView) findViewById(R.id.article_author);
        String articleAuthor = article.getAuthor();


        if (articleAuthor != null) {
            textView.setText(articleAuthor);
        }


        TextView textView2 = (TextView) findViewById(R.id.article_body);
        String articleDescription = article.getDescription();
        if (articleDescription != null) {
            textView2.setText(articleDescription);

        }

        Picasso.with(this).load(article.getUrlToImage()).into((ImageView) findViewById(R.id.photo));
    }



}
