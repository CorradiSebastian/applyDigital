package com.sebastiancorradi.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sebastiancorradi.myapplication.domain.model.Article

data class ArticleDTO(
    @SerializedName("objectID") val objectId: Int,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("comment_text") val commentText: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("created_at_i") val cratedAtI: Long,
    @SerializedName("parent_id") val parentId: Long,
    @SerializedName("children") val children: List<Long>?,
    @SerializedName("story_url") val storyUrl: String?,
) {
    fun toDomain() =
        Article(objectId,
            title = storyTitle?: title?:"",
            body = commentText?:"", //No se usa
            author = author?:"",
            createdAt = createdAt?:"",
            cratedTS = cratedAtI,
            parentId = parentId,//NO se usa
            children = children?:emptyList(),//NO se usa
            url = storyUrl
        )
}
