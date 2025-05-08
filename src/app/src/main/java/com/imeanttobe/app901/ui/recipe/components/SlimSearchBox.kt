package com.imeanttobe.app901.ui.recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SlimSearchBox(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    val textFieldColor = MaterialTheme.colorScheme.surfaceVariant

    BasicTextField(
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = contentColor),
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(contentColor),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
        }),
        decorationBox = @Composable { innerTextField ->
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = textFieldColor)
                    .padding(vertical = 5.dp, horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search Icon",
                    tint = contentColor
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                    if (text.isEmpty()) {
                        Text(
                            text = "검색...",
                            color = contentColor,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SlimSearchBoxPreview() {
    SlimSearchBox(
        text = "",
        onValueChange = {},
        onSearch = {}
    )
}