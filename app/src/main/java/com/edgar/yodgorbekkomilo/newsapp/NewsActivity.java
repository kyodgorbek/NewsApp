package com.edgar.yodgorbekkomilo.newsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by yodgorbekkomilov on 2/22/18.
 */

public class NewsActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        new mTask().execute();
        TextView textView = (TextView) findViewById(R.id.News);

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

