package com.edgar.yodgorbekkomilo.newsapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 3/11/18.
 */

public class AppWidgetProvider extends AppWidgetManager implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "ArticleWidgetProvider";

    List<String> mCollection = new ArrayList<>();
    Context mContext = null;
    Intent intent;

    public AppWidgetProvider(Context context, Intent intent) {


        mContext = context;
        this.intent = intent;
    }

    


    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, mCollection.get(position));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
        mCollection.clear();
        for (int i = 1; i <= 10; i++) {
            mCollection.add("ListView item " + i);
        }
    }

    public void onUpdate(Context context, AppWidgetProvider appWidgetManager, int[] appWidgetIds) {
    }

    public void updateAppWidget(int appWidgetId, RemoteViews views) {
    }
}
