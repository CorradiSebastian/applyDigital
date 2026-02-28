package com.sebastiancorradi.myapplication.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastiancorradi.myapplication.domain.model.Article
import java.util.concurrent.TimeUnit

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onRemove: (String) -> Unit
) {
    val state = rememberSwipeToDismissBoxState()
    LaunchedEffect(state.currentValue) {
        // Si el estado final es 'descartado hacia la izquierda'...
        if (state.currentValue == SwipeToDismissBoxValue.EndToStart) {
            // ...entonces llamamos a la función para borrar.
            onRemove(article.title)
        }
    }
    SwipeToDismissBox(
        modifier = modifier,
        state = state,
        enableDismissFromStartToEnd = false, // Desactiva deslizar a la derecha
        backgroundContent = {
            val color = when (state.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> Color.Red
                else -> Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar",
                    tint = Color.White
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RectangleShape,
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                ListItem(
                    modifier = Modifier.padding(vertical = 20.dp),
                    colors = androidx.compose.material3.ListItemDefaults.colors(
                        containerColor = Color.White
                    ),
                    headlineContent = { Text(text = article.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ) },
                    supportingContent = { Text(
                        text = getSupporttingContent(article),
                        color = Color.Gray,
                        fontSize = 12.sp
                    ) },
                )
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color.DarkGray,
            )
        }
    }
}
fun getSupporttingContent(article: Article): String{
    return article.author + " - " + getArticleAge(article.cratedTS)
}

fun getArticleAge(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - (timestamp * 1000)

    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)
    return when {
        // Menos de 60 minutos
        minutes < 60 -> "${minutes}m"

        // Entre 1 hora y 24 horas
        hours < 24 -> "${hours}h"

        // Entre 24 y 48 horas
        hours in 24..47 -> "yesterday"

        // Entre 48 horas (2 días) y 6 días
        days in 2..6 -> "${days}d"

        // Entre 7 días y 4 semanas (aprox 28 días)
        days in 7..27 -> "${days / 7}w"

        // Entre 4 semanas y 11 meses (aprox 330 días)
        days in 28..330 -> "${days / 30}mon"

        // Más de 11 meses
        else -> "${days / 365}y"
    }
}