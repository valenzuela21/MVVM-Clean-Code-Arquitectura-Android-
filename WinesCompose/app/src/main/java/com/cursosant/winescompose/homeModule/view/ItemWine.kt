package com.cursosant.winescompose.homeModule.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cursosant.winescompose.common.entities.Rating
import com.cursosant.winescompose.common.entities.Wine
import com.cursosant.winescompose.common.components.RatingBar
import com.cursosant.winescompose.common.components.TextList
import com.cursosant.winescompose.ui.theme.WinesComposeTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemWine(wine: Wine, modifier: Modifier) {
    Card(modifier = modifier) {
        Box(modifier = modifier) {
            GlideImage(model = wine.image,
                contentDescription = "Wine image",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .background(Brush.verticalGradient(
                    colors = listOf(Color(0xaa000000), Color(0xcc000000), Color.Black)
                ))) {
                RatingBar(rating = wine.rating.average.toFloat(), modifier = Modifier.scale(.75f))
                TextList(text = wine.wine, style = typography.titleLarge)
                TextList(text = wine.winery, style = typography.bodyLarge)
                TextList(text = wine.location.replace("\n·", ""), style = typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    WinesComposeTheme {
        ItemWine(
            wine = Wine("Castilla", "Liria", Rating("4.7", "Good"), "Spain", "", 1.0),
            modifier = Modifier
        )
    }
}