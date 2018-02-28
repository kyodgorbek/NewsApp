package com.edgar.yodgorbekkomilo.newsapp;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;

/**
 * Created by yodgorbekkomilov on 2/28/18.
 */

public class FavoriteArticle extends Article {




    protected FavoriteArticle(String in) {
        super(in);
    }

    public String getTitle(String string){
  return getTitle((cursor.getString(0)));
 }

 public void  setTitle(){

     this.getTitle((cursor.getString(0)));
 }

}
