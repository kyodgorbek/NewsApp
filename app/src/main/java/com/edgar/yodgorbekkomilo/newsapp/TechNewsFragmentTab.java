package com.edgar.yodgorbekkomilo.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yodgorbekkomilov on 2/21/18.
 */

public class TechNewsFragmentTab extends Fragment {


    private static final String GRID_VIEW_STATE = "GRID_VIEW_STATE";
    public ArrayList<News> articleTech;
    View parentView3;
    ArrayList<Article> articleArrayList = new ArrayList<>();
    Parcelable state2;
    GridView gridView;
    private ArticleAdapter adapter;
    private View view3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view3 = inflater.inflate(R.layout.tech_news_fragment_tab, container, false);

        gridView = view3.findViewById(R.id.listView);
        articleTech = new ArrayList<>();
        parentView3 = view3.findViewById(R.id.parentLayout);
        if (savedInstanceState != null) {
            state2 = savedInstanceState.getParcelable(GRID_VIEW_STATE);
        }
        /**
         * Getting List and Setting List Adapter
         *
         *
         */


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), NewsDetailActivity.class);
                i.putExtra("myDataKey", articleArrayList.get(position));
                startActivity(i);

            }
        });


        Toast toast = Toast.makeText(getActivity().getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


        return view3;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
            Call<News> call = api.getTechNews();

            /**
             * Enqueue Callback will be call when get response...
             */
            SharedPreferences appSharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(getContext());
            final SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
            adapter = new ArticleAdapter(getActivity(), articleArrayList);
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


                        Gson gson = new Gson();
                        String json = gson.toJson(news.getArticles());
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.commit();


                        /**
                         * Binding that List to Adapter
                         */
                        gridView.setAdapter(adapter);
                        if (state2 != null) {
                            gridView.onRestoreInstanceState(state2);
                        }


                    } else {
                        Snackbar.make(parentView3, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(parentView3, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();

        }
        // Place the code here
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putParcelable(GRID_VIEW_STATE, gridView.onSaveInstanceState());

    }
}





