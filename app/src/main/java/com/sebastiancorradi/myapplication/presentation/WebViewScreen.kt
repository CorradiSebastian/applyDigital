package com.sebastiancorradi.myapplication.presentation

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(url: String) {
    var hasError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    // Guardamos una referencia al WebView para poder controlarlo después
    val webView = remember { WebView(context) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasError) {
            // UI de Error
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error al cargar la página. Verifica tu conexión.")
            }
        } else {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true // Habilitar JS si es necesario
                        webViewClient =
                            WebViewClient()   // Para que los enlaces se abran dentro del WebView
                        loadUrl(url)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}