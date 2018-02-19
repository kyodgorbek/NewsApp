package com.edgar.yodgorbekkomilo.newsapp.Pojo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public interface ApiService {

    @GET("everything/?q=bitcoin&apiKey=6dca4d1389634b61a9d3bb265f9928eb")
    Call<Article> getMyJSON();
}
