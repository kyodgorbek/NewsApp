package com.edgar.yodgorbekkomilo.newsapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.edgar.yodgorbekkomilo.newsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 3/11/18.
 */

public class WidgetDataProvider extends AppWidgetProvider{




    public static String MY_WIDGET_UPDATE = "android.appwidget.action.MY_OWN_WIDGET_UPDATE";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        if(MY_WIDGET_UPDATE.equals(intent.getAction())){

            Bundle extras = intent.getExtras();
            if(extras!=null) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisAppWidget = new ComponentName(context.getPackageName(), WidgetDataProvider.class.getName());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

                onUpdate(context, appWidgetManager, appWidgetIds);
            }

            //Toast.makeText(context, "onReceiver()", Toast.LENGTH_LONG).show();
        }
    }


    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetId) {


        final int N = appWidgetId.length;
        for (int i = 0; i < N; i++) {
            RemoteViews remoteViews = updateAppWidgetListView(context, appWidgetId[i]);
            appWidgetManager.updateAppWidget(appWidgetId[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetId);
    }


    private RemoteViews updateAppWidgetListView(Context context, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.collection_widget);
        Intent svcIntent = new Intent(context, WidgetService.class);

        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(appWidgetId, R.id.article, svcIntent);
        remoteViews.setEmptyView(com.edgar.yodgorbekkomilo.newsapp.widget.WidgetDataProvider.R.id.listViewWidget, com.edgar.yodgorbekkomilo.newsapp.widget.WidgetDataProvider.R.id.empty_view);




  return remoteViews;


}




    // Instruct the widget manager to update the widget
    //  appWidgetManager.updateAppWidget(appWidgetId, views);


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyDataKey", Context.MODE_PRIVATE);

        // RemoteViewsService.RemoteViewsFactory remoteViewsFactory


        // There may be multiple widgets active, so update all of them
        for (int appWidgetIds : appWidgetId) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

