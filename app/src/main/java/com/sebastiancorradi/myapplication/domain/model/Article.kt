package com.sebastiancorradi.myapplication.domain.model

data class Article(
    val id: Int,
    val title: String,
    val suportingContent: String,
    val createdTS: Long,
    val url: String?
)