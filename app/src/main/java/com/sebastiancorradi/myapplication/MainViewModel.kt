package com.sebastiancorradi.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.usecase.DeleteArticleUseCase
import com.sebastiancorradi.myapplication.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
) : ViewModel() {
    private final val TAG = "MainViewModel"

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _articlesState = MutableStateFlow<List<Article>?>(null)
    val articlesState: StateFlow<List<Article>?> = _articlesState

    fun loadArticles() {
        viewModelScope.launch {
            try {
                val articles = getArticlesUseCase()
                _articlesState.value = articles
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                // TODO Manejar error
            }
        }
    }

    fun deleteArticle(article: Article?) {
        article?.let {
            viewModelScope.launch {
                deleteArticleUseCase(it)
                _articlesState.value = _articlesState.value?.filter { item -> item.id != it.id }
            }
        }
    }

    fun refreshArticles() {
        viewModelScope.launch {
            _isRefreshing.value = true
            val articles = getArticlesUseCase()
            _articlesState.value = articles
            _isRefreshing.value = false
        }
    }
}
