package org.hed.archdemo.repository.web;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hedong on 2017/11/30.
 */

public class RetrofitHelper {
    String API_BASE_URL = "https://api.github.com/";
    private Retrofit mRetrofit;
    public RetrofitHelper(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public GithubApi getGithubApi(){
        return mRetrofit.create(GithubApi.class);
    }
}
