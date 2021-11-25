package com.astriex.pokedex.di

import com.astriex.pokedex.data.remote.PokeApi
import com.astriex.pokedex.data.repository.PokemonRepositoryImpl
import com.astriex.pokedex.domain.repository.PokemonRepository
import com.astriex.pokedex.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePokeApi(): PokeApi =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build().create(PokeApi::class.java)

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokeApi): PokemonRepository = PokemonRepositoryImpl(api)

}