package com.sebastiancorradi.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.domain.usecase.GetArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {
    private final val TAG = "MainViewModel"

    private val _articlesState = MutableStateFlow<List<Article>?>(null)
    val articlesState: StateFlow<List<Article>?> = _articlesState

    fun loadArticles() {
        viewModelScope.launch {
            try {
                val articles = getArticlesUseCase()
                _articlesState.value = articles
                Log.e(TAG, "articles loaded: ${articles.size}")
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                // TODO Manejar error
            }
        }
    }
}