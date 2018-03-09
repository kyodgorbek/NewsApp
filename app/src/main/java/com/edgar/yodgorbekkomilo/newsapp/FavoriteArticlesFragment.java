package com.edgar.yodgorbekkomilo.newsapp;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yodgorbekkomilov on 3/9/18.
 */

public class FavoriteArticlesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_favorite, container, false);

        String queryUri = ArticleColumns.CONTENT_URI.toString();
        Cursor cursor =
                getActivity().getApplicationContext().getContentResolver().query(Uri.parse(queryUri), null, null,
                        null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Log.i("favorites", cursor.toString());
            }
        }

        return view; 
    }
}