package com.astriex.pokedex.presentation.pokemonList.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.astriex.pokedex.domain.model.PokedexListEntry
import com.astriex.pokedex.presentation.pokemonList.PokemonListViewModel
import com.astriex.pokedex.ui.theme.RobotoCondensed
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(Brush.verticalGradient(listOf(dominantColor, defaultDominantColor)))
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/{${dominantColor.toArgb()}/${entry.name}}"
                )
            }
    ) {
        Column(modifier = Modifier.align(Center)) {

            val painter = rememberCoilPainter(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
                    .target {
                        viewModel.calcDominantColor(it) { color ->
                            dominantColor = color
                        }
                    }
                    .build()
            )
            val painterState = painter.loadState

            Image(
                painter = painter,
                contentDescription = entry.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )
            if (painterState is ImageLoadState.Loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f))
            }
            Text(
                text = entry.name,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}