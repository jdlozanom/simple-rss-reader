package com.jdlozanom.simplerssreader.api;

import com.jdlozanom.simplerssreader.api.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {
    @GET("tag/feeds/rss2.xml")
    Call<Feed> getData();
}
