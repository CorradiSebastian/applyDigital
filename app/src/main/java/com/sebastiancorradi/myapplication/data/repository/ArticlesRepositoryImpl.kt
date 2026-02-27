package com.sebastiancorradi.myapplication.data.repository

import com.sebastiancorradi.myapplication.data.remote.ArticlesApi
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticlesApi
): ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        val response = api.getArticles()
        return response.articles.map { it.toDomain() }
    }
}