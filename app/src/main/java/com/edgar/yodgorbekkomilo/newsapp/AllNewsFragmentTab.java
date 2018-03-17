package com.edgar.yodgorbekkomilo.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.edgar.yodgorbekkomilo.newsapp.Adapter.ArticleAdapter;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.ApiService;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.RetroClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by yodgorbekkomilov on 2/21/18.
 */

public class AllNewsFragmentTab extends Fragment {

    public ArrayList<News> articleList;
    ArrayList<Article> articleArrayList = new ArrayList<>();
    public GridView gridView;
    private View parentView;
    private ArticleAdapter adapter;
    Parcelable state;


// ...

// restore index and position

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_news_fragment_tab, container, false);
        state = gridView.onSaveInstanceState();
        articleList = new ArrayList<>();

        parentView = view.findViewById(R.id.parentLayout);


        /**
         * Getting List and Setting List Adapter
         *
         *
         */

        gridView = view.findViewById(R.id.listView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Snackbar.make(parentView, articleArrayList.get(position).getStatus() + " => " + articleArrayList.get(position).getTotalResults(), Snackbar.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), NewsDetailActivity.class);


                i.putExtra("myDataKey", articleArrayList.get(position));
                startActivity(i);

            }
        });

        Toast toast = Toast.makeText(getActivity().getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


        /**
         * Checking Internet Connection
         */
        if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle(getString(R.string.string_getting_json_title));
            dialog.setMessage(getString(R.string.string_getting_json_message));
            dialog.dismiss();



            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<News> call = api.getAllNews();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        /**
                         * Got Successfully
                         */
                        // String articleList = String.valueOf(response.body());
                        News news = response.body();


                        articleArrayList.addAll(news.getArticles());


                        /**
                         * Binding that List to Adapter
                         */adapter = new ArticleAdapter(getActivity(), articleArrayList);
                        gridView.setAdapter(adapter);
                        if(state != null) {
                            gridView.onRestoreInstanceState(state);
                        }

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
        return view;
    }
}





