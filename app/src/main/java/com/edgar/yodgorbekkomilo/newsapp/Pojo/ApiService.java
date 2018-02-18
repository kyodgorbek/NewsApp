package com.edgar.yodgorbekkomilo.newsapp.Pojo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public interface ApiService {

    @GET("/json_data.json")
    Call<Article> getMyJSON();
}
