package com.astriex.pokedex.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Icons(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any
)