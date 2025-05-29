package com.imeanttobe.app901.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imeanttobe.app901.util.Formatter

@Composable
fun EmphasizedText(
    content: Any,
    color: Color = MaterialTheme.colorScheme.primary,
    tail: String = "",
    modifier: Modifier = Modifier,
) {
    val content: String =
        when (content) {
            is Int -> Formatter.formatPrice(price = content)
            is String -> content
            else -> content.toString()
        }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = color,
        )

        if (tail.isNotEmpty()) {
            Text(
                text = tail,
                style = MaterialTheme.typography.bodySmall,
                color = color,
            )
        }
    }
}
