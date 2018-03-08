package com.edgar.yodgorbekkomilo.newsapp;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.squareup.picasso.Picasso;

import static com.edgar.yodgorbekkomilo.newsapp.DatabaseHandler.articleID;
import static com.edgar.yodgorbekkomilo.newsapp.DatabaseHandler.articleName;

/**
 * Created by yodgorbekkomilov on 2/25/18.
 */

public class NewsDetailActivity extends AppCompatActivity {
    ImageButton addToFavoritesBtn;
   Article article;
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

        final Article article = (Article) getIntent().getParcelableExtra("myDataKey");

        FloatingActionButton share = (FloatingActionButton)findViewById(R.id.share_fab);
        ImageButton shareButton = (ImageButton)findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String articleDescription = article.getDescription() ;
                String articleTitle = article.getTitle();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, articleDescription);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, articleTitle);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String articleDescription = article.getDescription() ;
                String articleTitle = article.getTitle();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, articleDescription);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, articleTitle);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
        TextView textView = (TextView) findViewById(R.id.article_title);
        final String articleTitle = article.getTitle();

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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(articleUrl));
                startActivity(intent);
            }
        });

        Picasso.with(this).load(article.getUrlToImage()).into((ImageView) findViewById(R.id.photo));

        addToFavoritesBtn = (ImageButton) findViewById(R.id.favorite_button);
        addToFavoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(ArticleColumns._ID); //Value bro example 1
                values.put(ArticleColumns.TITLE,  ); // name
                values.put(ArticleColumns.TITLE_DESCRIPTION, );
                values.put(ArticleColumns.AUTHOR,);
                values.put(ArticleColumns.IS_FAVOURITE);
                getContentResolver().insert(ArticleColumns.CONTENT_URI, values);
            }
        });
    }
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



