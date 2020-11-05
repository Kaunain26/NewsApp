package com.knesarcreation.newsapp.api

import com.knesarcreation.newsapp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getTopHeadLines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<News>

    @GET("everything")
    fun getNews(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Call<News>
}