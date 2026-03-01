package com.sebastiancorradi.myapplication.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebastiancorradi.myapplication.data.remote.dto.ArticleDTO
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.utils.getSupporttingContent

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String?,
    val createdAtI: Long,
    val url: String?,
    val deleted: Boolean = false
)

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        suportingContent = getSupporttingContent(author?:"", createdAtI),
        createdTS = createdAtI,
        url = url
    )
}

fun ArticleDTO.toEntity(): ArticleEntity {
    return ArticleEntity(
        id = objectId,
        title = storyTitle ?: title ?: "",
        author = author,
        createdAtI = createdAtI,
        url = storyUrl
    )
}
