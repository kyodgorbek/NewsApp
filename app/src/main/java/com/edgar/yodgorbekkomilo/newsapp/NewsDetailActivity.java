package com.edgar.yodgorbekkomilo.newsapp;

import android.app.Activity;
import android.os.Bundle;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;

/**
 * Created by yodgorbekkomilov on 2/25/18.
 */

public class NewsDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        Article article = (Article) getIntent().getParcelableExtra("myDataKey");
    }
}