package com.edgar.yodgorbekkomilo.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by yodgorbekkomilov on 2/22/18.
 */


public class NewsActivity extends AppCompatActivity {


    ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        imageView = (ImageView) findViewById(R.id.imageViewBackground);
        progressBar = findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.News);
        new MyAsyncTask().execute();

        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
        protected void onPreExecute() {
            // Runs on the UI thread before doInBackground
            // Good for toggling visibility of a progress indicator
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        protected Bitmap doInBackground(String... strings) {
            // Some long-running task like downloading an image.
            Bitmap tempBMP = BitmapFactory.decodeResource(getResources(), R.drawable.news);
            return tempBMP;
        }

        protected void onProgressUpdate(Integer... values) {
            // Executes whenever publishProgress is called from doInBackground
            // Used to update the progress indicator
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(Bitmap result) {
            // This method is executed in the UIThread
            // with access to the result of the long running task
            imageView.setImageBitmap(result);
            // Hide the progress bar
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

    }
}
