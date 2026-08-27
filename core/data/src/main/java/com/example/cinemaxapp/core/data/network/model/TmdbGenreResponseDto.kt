package com.example.cinemaxapp.core.data.network.model

import com.google.gson.annotations.SerializedName


data class TmdbGenreResponseDto(
    @SerializedName("genres") val genres: List<TmdbGenreDto>? = null,
)


data class TmdbGenreDto(
    @SerializedName("id")   val id: Int,
    @SerializedName("name") val name: String,
)
