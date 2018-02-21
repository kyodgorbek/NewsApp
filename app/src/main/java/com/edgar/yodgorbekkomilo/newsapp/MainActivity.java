package com.edgar.yodgorbekkomilo.newsapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.edgar.yodgorbekkomilo.newsapp.Adapter.ArticleAdapter;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.ApiService;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.RetroClient;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {
        private ListView listView;
        private View parentView;

        private ArrayList<News> articleList;
        private ArticleAdapter adapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);


                TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
                tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
                tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
                tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                final PagerAdapter adapter = new PageAdapter
                        (getSupportFragmentManager(), tabLayout.getTabCount());
                viewPager.setAdapter(adapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                                viewPager.setCurrentItem(tab.getPosition());
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

                return super.onOptionsItemSelected(item);
        }

                articleList = new ArrayList<>();

                parentView = findViewById(R.id.parentLayout);

                /**
                 * Getting List and Setting List Adapter
                 */
                listView = (ListView) findViewById(R.id.listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Snackbar.make(parentView, articleList.get(position).getStatus() + " => " + articleList.get(position).getTotalResults(), Snackbar.LENGTH_LONG).show();
                        }
                });

                /**
                 * Just to know onClick and Printing Hello Toast in Center.
                 */
                Toast toast = Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                assert fab != null;
                fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(@NonNull final View view) {

                                /**
                                 * Checking Internet Connection
                                 */
                                if (InternetConnection.checkConnection(getApplicationContext())) {
                                        final ProgressDialog dialog;
                                        /**
                                         * Progress Dialog for User Interaction
                                         */
                                        dialog = new ProgressDialog(MainActivity.this);
                                        dialog.setTitle(getString(R.string.string_getting_json_title));
                                        dialog.setMessage(getString(R.string.string_getting_json_message));
                                        dialog.show();

                                        //Creating an object of our api interface
                                        ApiService api = RetroClient.getApiService();

                                        /**
                                         * Calling JSON
                                         */
                                        Call<News> call = api.getMyJSON();

                                        /**
                                         * Enqueue Callback will be call when get response...
                                         */
                                        call.enqueue(new Callback<News>() {
                                                @Override
                                                public void onResponse(Call<News> call, Response<News> response) {
                                                        //Dismiss Dialog
                                                        dialog.dismiss();

                                                        if(response.isSuccessful()) {
                                                                /**
                                                                 * Got Successfully
                                                                 */
                                                                // String articleList = String.valueOf(response.body());
                                                                News news = response.body();

                                                                ArrayList<Article> articleArrayList = new ArrayList<>();
                                                                articleArrayList.addAll(news.getArticles());







                                                                /**
                                                                 * Binding that List to Adapter
                                                                 */
                                                                adapter = new ArticleAdapter(MainActivity.this, articleArrayList);
                                                                listView.setAdapter(adapter);

                                                        } else {
                                                                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                                                        }
                                                }

                                                @Override
                                                public void onFailure(Call<News> call, Throwable t) {
                                                        dialog.dismiss();
                                                }
                                        });

                                } else {
                                        Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                                }
                        }
                });
        }

}









