package com.sebastiancorradi.myapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sebastiancorradi.myapplication.domain.model.Article

data class ArticleDTO(
    @SerializedName("story_id") val storyId: Int,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("comment_text") val commentText: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("created_at_i") val cratedAtI: Long ,
    @SerializedName("parent_id") val parentId: Long?,
    @SerializedName("children") val children: List<Long>?,
) {
    fun toDomain() =
        Article(storyId, storyTitle, commentText, author, createdAt, cratedAtI, parentId, children)
}