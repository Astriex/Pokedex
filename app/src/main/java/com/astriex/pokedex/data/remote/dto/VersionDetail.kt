package com.astriex.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    val rarity: Int,
    val version: VersionX
)