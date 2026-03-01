package com.sebastiancorradi.myapplication.domain.usecase

import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.repository.ArticlesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteArticleUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.deleteArticle(article)
    }
}
