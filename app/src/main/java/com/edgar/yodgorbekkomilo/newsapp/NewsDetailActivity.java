package com.edgar.yodgorbekkomilo.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by yodgorbekkomilov on 2/25/18.
 */

public class NewsDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        Article article = (Article) getIntent().getParcelableExtra("myDataKey");
        TextView textView = (TextView)findViewById(R.id.article_title);
        String articleTitle = article.getTitle();

        if (articleTitle != null) {
            textView.setText(articleTitle);
        }

        TextView textView2 = (TextView)findViewById(R.id.article_body);
        String articleDescription = article.getDescription();
        if(articleDescription != null){
            textView2.setText(articleDescription);

        }

        Picasso.with(this).load(article.getUrlToImage()).into((ImageView) findViewById(R.id.imageView));
    }

}