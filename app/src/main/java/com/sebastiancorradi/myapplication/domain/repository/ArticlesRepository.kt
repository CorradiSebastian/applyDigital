package com.sebastiancorradi.myapplication.domain.repository

import com.sebastiancorradi.myapplication.domain.model.Article

interface ArticlesRepository {
    suspend fun getArticles(): List<Article>
    suspend fun deleteArticle(article: Article)
}
