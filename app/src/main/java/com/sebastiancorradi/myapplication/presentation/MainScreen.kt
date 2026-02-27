package com.sebastiancorradi.myapplication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastiancorradi.myapplication.MainViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    val viewModel: MainViewModel = hiltViewModel()
    val articles = viewModel.articlesState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }
    Column(modifier) {
        Text(
            text = "Hello Sebas"
        )
        if (articles.value?.size?:0 > 0){
            val size = articles.value?.size?:0
            LazyColumn {
                items(size) { index ->
                    //ArticleItem(article)
                    Text(text = "Article $index: ${articles.value?.get(index)?.title}")
                }
            }
        }
    }

}