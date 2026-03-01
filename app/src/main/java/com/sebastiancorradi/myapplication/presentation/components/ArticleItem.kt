package com.sebastiancorradi.myapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
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
                /*Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar",
                    tint = Color.White
                )*/
                Text("Borrar")
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
                        text = article.suportingContent,
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
