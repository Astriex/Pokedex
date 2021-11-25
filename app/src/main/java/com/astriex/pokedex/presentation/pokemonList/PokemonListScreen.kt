package com.astriex.pokedex.presentation.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.astriex.pokedex.R
import com.astriex.pokedex.domain.model.PokedexListEntry
import com.astriex.pokedex.presentation.pokemonList.components.PokedexEntry
import com.astriex.pokedex.presentation.pokemonList.components.SearchBar

@Composable
fun PokemonListScreen(
    navController: NavController
) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "Pokemon logo",
                Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
            PokedexEntry(
                entry = PokedexListEntry(
                    "huha",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    1
                ), navController = navController
            )

        }
    }
}