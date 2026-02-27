package com.sebastiancorradi.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.sebastiancorradi.myapplication.data.remote.ArticlesApi
import com.sebastiancorradi.myapplication.data.remote.RetrofitClient
import com.sebastiancorradi.myapplication.data.repository.ArticlesRepositoryImpl
import com.sebastiancorradi.myapplication.domain.usecase.GetArticlesUseCase
import com.sebastiancorradi.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    private var isReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val articlesApi = RetrofitClient.articlesApi

        val viewModel: MainViewModel = MainViewModel(GetArticlesUseCase(ArticlesRepositoryImpl(articlesApi)))
        // Add a process (e.g., a simulated 2-second load)
        lifecycleScope.launch {
            delay(2000)
            isReady = true
        }

        // Keep the splash screen visible until isReady is true
        splashScreen.setKeepOnScreenCondition {
            !isReady
        }

        enableEdgeToEdge()


        setContent {
            LaunchedEffect(Unit) {
                viewModel.loadArticles()
            }

            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}*/
