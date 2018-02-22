package com.edgar.yodgorbekkomilo.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yodgorbekkomilov on 2/22/18.
 */

public class NewsActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        TextView textView = (TextView)findViewById(R.id.News);

        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
}
    }