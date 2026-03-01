package com.sebastiancorradi.myapplication.data.repository

import com.sebastiancorradi.myapplication.data.local.dao.ArticleDao
import com.sebastiancorradi.myapplication.data.local.entities.toDomain
import com.sebastiancorradi.myapplication.data.local.entities.toEntity
import com.sebastiancorradi.myapplication.data.remote.ArticlesApi
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticlesApi,
    private val dao: ArticleDao
): ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        return try {
            val response = api.getArticles()
            val entities = response.articles.map { it.toEntity() }
            dao.clearAll()
            dao.insertArticles(entities)
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            dao.getUnDeletedArtilesArticles().map { it.toDomain() }
        }
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article.id)
    }
}
