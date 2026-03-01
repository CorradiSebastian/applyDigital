package com.sebastiancorradi.myapplication.presentation

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastiancorradi.myapplication.MainViewModel
import com.sebastiancorradi.myapplication.presentation.components.ArticleItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val viewModel: MainViewModel = hiltViewModel()
    val articles by viewModel.articlesState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }

    Column(modifier) {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { viewModel.refreshArticles() },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    items = articles ?: emptyList(),
                    key = { it.id }
                ) { article ->
                    ArticleItem(
                        article = article,
                        modifier = Modifier.animateItem(
                            fadeInSpec = tween(durationMillis = 300),
                            fadeOutSpec = tween(durationMillis = 300)
                        ),
                        onRemove = {
                            Log.e("ArticleScreen", "Borrando: ${article.title}")
                            viewModel.deleteArticle(article)
                        }
                    )
                }
            }
        }
    }
}
