package com.edgar.yodgorbekkomilo.newsapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

/**
 * Created by yodgorbekkomilov on 2/21/18.
 */

public class SportNewsFragmentTab extends Fragment {
    public ArrayList<News> articleSport;
    private ArticleAdapter adapter;
   View parentView2;
    private View view2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
View  view2 =      inflater.inflate(R.layout.sport_news_fragment_tab, container, false);
        articleSport = new ArrayList<>();

        parentView2 = view2.findViewById(R.id.parentLayout);

        /**
         * Getting List and Setting List Adapter
         *
         *
         */

        final GridView gridView = (GridView) view2.findViewById(R.id.listView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView2, articleSport.get(position).getStatus() + " => " + articleSport.get(position).getTotalResults(), Snackbar.LENGTH_LONG).show();
            }
        });


        Toast toast = Toast.makeText(getActivity().getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FloatingActionButton fab = (FloatingActionButton) view2.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {

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
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiService();

                    /**
                     * Calling JSON
                     */
                    Call<News> call = api.getSportNews();

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

                                ArrayList<Article> articleArrayList = new ArrayList<>();
                                articleArrayList.addAll(news.getArticles());


                                /**
                                 * Binding that List to Adapter
                                 */adapter = new ArticleAdapter(getActivity(), articleArrayList);
                                gridView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView2, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<News> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    Snackbar.make(parentView2, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();

                }
            }
        });
        return view2;
    }
}






