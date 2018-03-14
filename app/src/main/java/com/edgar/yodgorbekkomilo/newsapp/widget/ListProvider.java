package com.edgar.yodgorbekkomilo.newsapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.edgar.yodgorbekkomilo.newsapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yodgorbekkomilov on 3/13/18.
 */


public class ListProvider implements RemoteViewsService.RemoteViewsFactory {



    public List<Article> article = new ArrayList<Article>();
    Object[] myArrayList = article.toArray();

    Context context = null;


    public ListProvider(Context context, Intent intent) {
        this.context = context;
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        //populateRecipeItem();
    }

    //  private Void populateRecipeItem() {
//        for (int i = 0; i < 10; i++) {
    //          RecipeItem recipeItem = new RecipeItem();
    //        recipeItem.ingredients = String.valueOf(+i);

//        }

    //      ingredients.add(myArrayList);


    //}


    @Override
    public void onCreate() {

    }


    @Override
    public void onDataSetChanged() {
        // Reading the Ingredients from the SharedPreferences
        SharedPreferences appSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = appSharedPreferences.getString("MyObject", "");
        Gson gson = new Gson();
        article = gson.fromJson(json, new TypeToken<List<Article>>() {
        }.getType());
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if( article == null){
return  0;
        }
        return article.size();
    }


    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.article_widget);
        Article articles = article.get(position);

        remoteViews.setTextViewText(R.id.article, articles.getTitle());
        try {
            Bitmap bitmap = Picasso.with(context).load(articles.getUrlToImage()).get();
            remoteViews.setImageViewBitmap(R.id.imageView, bitmap);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return remoteViews;
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
}
