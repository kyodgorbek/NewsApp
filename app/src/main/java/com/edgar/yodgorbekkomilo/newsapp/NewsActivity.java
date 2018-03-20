package com.edgar.yodgorbekkomilo.newsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

/**
 * Created by yodgorbekkomilov on 2/22/18.
 */


public class NewsActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);


        new mTask().execute();
        TextView textView = (TextView) findViewById(R.id.News);
        News news = new News();
        news.setStatus("status");
        news.setTotalResults(Integer.valueOf("totalResults"));
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, news.getStatus());
        bundle.putInt(FirebaseAnalytics.Param.ITEM_NAME, news.getTotalResults());
        //Logs an app event.
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //Sets whether analytics collection is enabled for this app on this device.
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);

        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
        mFirebaseAnalytics.setMinimumSessionDuration(20000);

        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
        mFirebaseAnalytics.setSessionTimeoutDuration(500);

        //Sets the user ID property.
        mFirebaseAnalytics.setUserId(String.valueOf(news.getStatus()));

        //Sets a user property to a given value.
        mFirebaseAnalytics.setUserProperty("Total results", String.valueOf(news.getTotalResults()));



        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    class mTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            return null;
        }

        protected void onPostExecute(Void... params) {
            Intent mainClass = new Intent(NewsActivity.this, MainActivity.class);
            startActivity(mainClass);
            finish();
        }

    }
}

