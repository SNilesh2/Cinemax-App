package com.example.cinemaxapp.core.data.network.model

import com.google.gson.annotations.SerializedName


data class TmdbCreditsDto(
    @SerializedName("cast") val cast: List<TmdbCastMemberDto> = emptyList(),
    @SerializedName("crew") val crew: List<TmdbCrewMemberDto> = emptyList(),
)


data class TmdbCastMemberDto(
    // TMDB person ID
    @SerializedName("id")           val id: Int,

    @SerializedName("name")         val name: String,

    // The character this person plays in the movie.
    @SerializedName("character")    val character: String?,

    @SerializedName("profile_path") val profilePath: String?,


    // TMDB billing order — 0 = top-billed actor. Used to sort the cast list.
    // Cast members are displayed in ascending order (0 = first shown)
    @SerializedName("order")        val order: Int = 0,


    // TMDB credit_id — unique identifier for this specific movie+person+character combination.
    @SerializedName("credit_id")    val creditId: String,
)


data class TmdbCrewMemberDto(
    // TMDB person ID
    @SerializedName("id")           val id: Int,

    @SerializedName("name")         val name: String,

    // "Directing", "Writing", "Production", "Camera". Used for filtering.
    @SerializedName("department")   val department: String?,

    // Specific job title, e.g. "Director", "Screenplay", "Producer". Used for filtering
    @SerializedName("job")          val job: String?,

    @SerializedName("profile_path") val profilePath: String?,


    @SerializedName("credit_id")    val creditId: String,
)
