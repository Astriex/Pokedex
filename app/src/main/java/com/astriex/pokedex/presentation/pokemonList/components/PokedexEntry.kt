package com.astriex.pokedex.presentation.pokemonList.components


import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.bitmap.BitmapPool
import coil.compose.rememberImagePainter
import coil.transform.Transformation
import com.astriex.pokedex.domain.model.PokedexListEntry
import com.astriex.pokedex.presentation.pokemonList.PokemonListViewModel
import com.astriex.pokedex.ui.theme.RobotoCondensed

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface.toArgb()
    var dominantColor by rememberSaveable {
        mutableStateOf(defaultDominantColor)
    }
    var isLoading by rememberSaveable { mutableStateOf(true) }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(dominantColor),
                        Color(defaultDominantColor),
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${dominantColor}/${entry.name}"
                )
            }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
            ) {
                if (isLoading)
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center),
                        color = MaterialTheme.colors.primary
                    )

                Image(
                    painter = rememberImagePainter(
                        data = entry.imageUrl,
                        builder = {
                            crossfade(true)
                            transformations(
                                object : Transformation {
                                    override fun key(): String {
                                        return entry.imageUrl
                                    }

                                    override suspend fun transform(
                                        pool: BitmapPool,
                                        input: Bitmap,
                                        size: coil.size.Size
                                    ): Bitmap {
                                        viewModel.calcDominantColor(input) { color ->
                                            dominantColor = color.toArgb()
                                            isLoading = false
                                        }
                                        return input
                                    }
                                }
                            )
                        }
                    ),
                    contentDescription = entry.name,
                    modifier = Modifier
                        .size(120.dp)
                )
            }

            Text(
                text = entry.name,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}