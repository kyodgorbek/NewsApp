package com.edgar.yodgorbekkomilo.newsapp.Pojo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yodgorbekkomilov on 2/18/18.
 */

public class RetroClient {

    private static final String ROOT_URL = "https://newsapi.org/v2/"; //https://newsapi.org/v2/everything/?q=bitcoin&apiKey=6dca4d1389634b61a9d3bb265f9928eb
    //https://newsapi.org/v2/everything/?q=bitcoin&apiKey=6dca4d1389634b61a9d3bb265f9928eb
    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

}
