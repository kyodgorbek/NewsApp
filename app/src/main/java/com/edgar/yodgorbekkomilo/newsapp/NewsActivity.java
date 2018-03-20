package com.edgar.yodgorbekkomilo.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;

import java.io.InputStream;
import java.util.List;


/**
 * Created by yodgorbekkomilov on 2/22/18.
 */


public class NewsActivity extends AppCompatActivity {


    private TextView textView;
    private ImageView imageView;
 ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewBackground);


        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<String, Progress, Bitmap> {
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

        protected void onProgressUpdate(Progress... values) {
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
