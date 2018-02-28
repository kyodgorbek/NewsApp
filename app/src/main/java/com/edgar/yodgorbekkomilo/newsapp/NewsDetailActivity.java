package com.edgar.yodgorbekkomilo.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        ab.setDisplayHomeAsUpEnabled(true);

        // Enable the Up button

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Article article = (Article) getIntent().getParcelableExtra("myDataKey");
        TextView textView = (TextView) findViewById(R.id.article_title);
        String articleTitle = article.getTitle();

        if (articleTitle != null) {
            textView.setText(articleTitle);
        }
        TextView textView1 = (TextView) findViewById(R.id.article_author);
        final String articleAuthor = article.getAuthor();


        if (articleAuthor != null) {
            textView1.setText(articleAuthor);
        }


        TextView textView2 = (TextView) findViewById(R.id.article_body);
        String articleDescription = article.getDescription();
        if (articleDescription != null) {
            textView2.setText(articleDescription);

        }
        Button button = (Button) findViewById(R.id.article_url);
        final String articleUrl = article.getUrl();
        if (articleUrl != null) {
            button.setText(articleUrl);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(articleUrl));
                startActivity(intent);
            }
        });

        Picasso.with(this).load(article.getUrlToImage()).into((ImageView) findViewById(R.id.photo));

     //   String articlePublisheAt = article.getPublishedAt();
       // TextView textView3 = (TextView) findViewById(R.id.textPublisher);
       // if (articlePublisheAt != null) {
         //   textView3.setText(articlePublisheAt);
 //<TextView
   //     android:id="@+id/textPublisher"
     //   style="?android:attr/textAppearanceLarge"
       // android:layout_width="match_parent"
       // android:layout_height="wrap_content"
       // android:textColor="#fff"
        //android:textStyle="bold"
        //android:textSize="20sp"
        //android:lineSpacingMultiplier="0.9"/>

        //}

    }

}
