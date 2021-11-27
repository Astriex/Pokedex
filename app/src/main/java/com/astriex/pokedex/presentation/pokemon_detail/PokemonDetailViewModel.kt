package com.astriex.pokedex.presentation.pokemon_detail

import androidx.lifecycle.ViewModel
import com.astriex.pokedex.data.remote.dto.Pokemon
import com.astriex.pokedex.domain.repository.PokemonRepository
import com.astriex.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        return repository.getPokemonInfo(name)
    }
}