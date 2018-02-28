package com.edgar.yodgorbekkomilo.newsapp;

import android.os.Parcel;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;

/**
 * Created by yodgorbekkomilov on 2/28/18.
 */

public class FavoriteArticle extends Article {
    protected FavoriteArticle(Parcel in) {
        super(in);
    }


 public FavoriteArticle(String title){
     super(title);

 }

 

}
