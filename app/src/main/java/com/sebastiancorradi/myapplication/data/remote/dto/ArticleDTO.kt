package com.sebastiancorradi.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.utils.getSupporttingContent

data class ArticleDTO(
    @SerializedName("objectID") val objectId: Int,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at_i") val createdAtI: Long,
    @SerializedName("story_url") val storyUrl: String?,
) {
    fun toDomain() =
        Article(
            id =objectId,
            title = storyTitle?: title?:"",
            suportingContent = getSupporttingContent(author?:"", createdAtI),
            createdTS = createdAtI,
            url = storyUrl
        )
}
