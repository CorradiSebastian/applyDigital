package com.sebastiancorradi.myapplication.data.remote

import retrofit2.http.GET

interface ArticlesApi {
    //https://hn.algolia.com/api/v1/search_by_date?query=mobile
    @GET("search_by_date?query=mobile")//TODO add page number
    suspend fun getArticles(): ArticlesResponse
}