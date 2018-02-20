package com.edgar.yodgorbekkomilo.newsapp;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.News;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yodgorbekkomilov on 2/20/18.
 */

public class NewsList {


    public class EmployeeList {
        @SerializedName("employeeList")
        private ArrayList<News> newsList;

        public ArrayList<News> getNewsList() {
            return newsList;
        }

        public void setNewsList(ArrayList<News> newsList) {
            this.newsList = newsList;
        }
    }

}
