package com.astriex.pokedex.data.repository

import com.astriex.pokedex.data.remote.PokeApi
import com.astriex.pokedex.data.remote.dto.Pokemon
import com.astriex.pokedex.data.remote.dto.PokemonList
import com.astriex.pokedex.domain.PokemonRepository
import com.astriex.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error(message = "An unknown error occurred")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(name)
        } catch (e: Exception) {
            return Resource.Error(message = "An unknown error occurred")
        }
        return Resource.Success(response)
    }

}