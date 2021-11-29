package com.astriex.pokedex.presentation.pokemon_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.astriex.pokedex.R
import kotlin.math.round

@Composable
fun PokemonDetailDataSection(
    pokemonWeight: Int,
    pokemonHeight: Int,
    sectionHeight: Dp = 80.dp
) {
    val pokemonWeightInKg = remember {
        round(pokemonWeight * 100 / 1000f)
    }
    val pokemonHeightInMeters = remember {
        round(pokemonHeight * 100 / 1000f)
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        PokemonDetailDataItem(
            dataValue = pokemonWeightInKg, dataUnit = "kg", dataIcon = painterResource(
                id = R.drawable.ic_weight
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(
            modifier = Modifier
                .size(width = 1.dp, height = sectionHeight)
                .background(Color.LightGray)
        )
        PokemonDetailDataItem(
            dataValue = pokemonHeightInMeters, dataUnit = "m", dataIcon = painterResource(
                id = R.drawable.ic_height
            ),
            modifier = Modifier.weight(1f)
        )
    }
}