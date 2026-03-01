package com.sebastiancorradi.myapplication

import android.R.attr.type
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sebastiancorradi.myapplication.presentation.MainScreen
import com.sebastiancorradi.myapplication.presentation.WebViewScreen
import com.sebastiancorradi.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

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

            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //MainScreen(modifier = Modifier.padding(innerPadding))
                    mainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun mainContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen") {
        composable("MainScreen") {
            MainScreen(
                modifier = modifier,
                onArticleClick = { url ->
                    // Navega a la pantalla de WebView pasando la URL
                    val encodedUrl = Uri.encode(url, "UTF-8")
                    navController.navigate("WebViewScreen/$encodedUrl")
                })
        }
        composable(route = "WebViewScreen/{url}",
            arguments = listOf(navArgument("url"){ type = NavType.StringType } )){ backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(url = url)
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}*/
