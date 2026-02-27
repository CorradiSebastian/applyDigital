package com.sebastiancorradi.myapplication.data.remote

import com.google.gson.annotations.SerializedName
import com.sebastiancorradi.myapplication.data.remote.dto.ArticleDTO

data class ArticlesResponse(
    @SerializedName("hits") val articles: List<ArticleDTO>,
    //.... other fields, tath I will not use
)