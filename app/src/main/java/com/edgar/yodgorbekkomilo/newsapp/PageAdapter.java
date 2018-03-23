package com.edgar.yodgorbekkomilo.newsapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by yodgorbekkomilov on 2/21/18.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs; 

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllNewsFragmentTab allNewsFragmentTab = new AllNewsFragmentTab();
                return allNewsFragmentTab;
            case 1:
                TechNewsFragmentTab techNewsFragmentTab = new TechNewsFragmentTab();
                return techNewsFragmentTab;
            case 2:
                SportNewsFragmentTab sportNewsFragmentTab = new SportNewsFragmentTab();
                return sportNewsFragmentTab;
            case 3:
                return new FavoriteArticlesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

