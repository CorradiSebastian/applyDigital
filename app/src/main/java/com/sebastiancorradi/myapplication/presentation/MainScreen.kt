package com.sebastiancorradi.myapplication.presentation

import android.util.Log
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastiancorradi.myapplication.MainViewModel
import com.sebastiancorradi.myapplication.domain.model.Article
import com.sebastiancorradi.myapplication.presentation.components.ArticleItem

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val viewModel: MainViewModel = hiltViewModel()
    val articles by viewModel.articlesState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }
    Column(modifier) {
        if (articles?.size?:0 > 0){
            LazyColumn {
                items(articles?: emptyList(),
                key = {it.id}) {article ->
                    //ArticleItem(article)
                    ArticleItem(modifier = Modifier.animateItem(
                            fadeInSpec = tween(durationMillis = 3000),
                            fadeOutSpec = tween(durationMillis = 3000)
                        ),
                        article = article) {
                        Log.e("ArticleScreen", "Borrando: $it")
                        viewModel.deleteArticle(article)
                    }
                }
            }
        }
    }

}


