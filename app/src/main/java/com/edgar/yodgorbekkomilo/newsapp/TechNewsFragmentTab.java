package com.edgar.yodgorbekkomilo.newsapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yodgorbekkomilov on 2/21/18.
 */

public class TechNewsFragmentTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tech_news_fragment_tab, container, false);
    }
}
