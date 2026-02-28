package com.sebastiancorradi.myapplication.domain.model

data class Article(
    val id: Int,
    val title: String,
    val body: String,
    val author: String,
    val createdAt: String,
    val cratedTS: Long,
    val parentId: Long?,
    val children: List<Long>,
    val url: String?
)