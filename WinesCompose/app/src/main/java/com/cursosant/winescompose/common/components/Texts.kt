package com.cursosant.winescompose.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun TextList(text: String, style: TextStyle) {
    Text(text = text, modifier = Modifier
        .padding(horizontal = 8.dp), color = Color.White, style = style)
}