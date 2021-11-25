package com.astriex.pokedex.domain

import com.astriex.pokedex.data.remote.dto.Pokemon
import com.astriex.pokedex.data.remote.dto.PokemonList
import com.astriex.pokedex.util.Resource

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>

    suspend fun getPokemonInfo(name: String): Resource<Pokemon>
}