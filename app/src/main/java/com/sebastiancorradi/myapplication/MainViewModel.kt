package com.sebastiancorradi.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {
    private final val TAG = "MainViewModel"

    private val _articlesState = MutableStateFlow<List<Article>?>(null)
    val articlesState: StateFlow<List<Article>?> = _articlesState

    fun loadArticles() {
        viewModelScope.launch {
            try {
                val articles = getArticlesUseCase()
                Log.e(TAG, "articles loaded: ${articles.size}")
                for(article in articles){
                    Log.e(TAG, "articleId: ${article.id}")
                }
                _articlesState.value = articles
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                // TODO Manejar error
            }
        }
    }

    fun deleteArticle(article: Article?) {
        Log.e(TAG, "deleteArticle: $article")
        val newITems = _articlesState.value?.filter { it != article }
        _articlesState.value = newITems
    }
        /*viewModelScope.launch {

        }*/
}