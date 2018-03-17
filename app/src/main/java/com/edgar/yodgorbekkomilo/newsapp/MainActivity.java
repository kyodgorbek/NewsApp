package com.edgar.yodgorbekkomilo.newsapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recovering instantState
        if(savedInstanceState != null){
            position = savedInstanceState.getInt("position_tab");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// I  used code below app showing empty screen
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.all_news_tab_title)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tech_news_tab_title)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.sport_news_tab_title)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.favorite_news_tab_title)));
       // tabLayout.getResources().getText(R.string.all_news_tab_title);
        //tabLayout.getResources().getText(R.string.tech_news_tab_title);
        //tabLayout.getResources().getText(R.string.sport_news_tab_title);
        //tabLayout.getResources().getText(R.string.favorite_news_tab_title);
// but working below code what is your suggestion
       //tabLayout.addTab(tabLayout.newTab().setText("AllNews"));
        //tabLayout.addTab(tabLayout.newTab().setText(" TechNews"));
       // tabLayout.addTab(tabLayout.newTab().setText("SportNews"));
       // tabLayout.addTab(tabLayout.newTab().setText("Favorite"));
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return false;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
    savedInstanceState.getInt("position_tab");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
            outState.putInt( "position_tab", position);
            super.onSaveInstanceState(outState);
    }
}
