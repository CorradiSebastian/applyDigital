package com.sebastiancorradi.myapplication.domain.usecase

import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.repository.ArticlesRepository

class GetArticlesUseCase(
    private val repository: ArticlesRepository
) {
    suspend operator fun invoke(): List<Article> {
        return repository.getArticles()
    }
}