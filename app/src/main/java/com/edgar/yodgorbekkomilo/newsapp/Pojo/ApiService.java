package com.edgar.yodgorbekkomilo.newsapp.Pojo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public interface ApiService {

    @GET("everything/?q=bitcoin&apiKey=6dca4d1389634b61a9d3bb265f9928eb")
        //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=API_KEY
        //https://newsapi.org/v2/sources/technology?6dca4d1389634b61a9d3bb265f9928eb"
    Call<News> getAllNews();

    @GET("top-headlines?category=technology&apiKey=6dca4d1389634b61a9d3bb265f9928eb")
        //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=API_KEY
        //https://newsapi.org/v2/sources/technology?6dca4d1389634b61a9d3bb265f9928eb"
    Call<News> getTechNews();

    @GET("top-headlines?category=sport&apiKey=6dca4d1389634b61a9d3bb265f9928eb")
        //https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=API_KEY
    Call<News> getSportNews();
}
