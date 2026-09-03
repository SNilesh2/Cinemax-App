package com.example.cinemaxapp.core.data.local.database.entity


import com.example.cinemaxapp.core.data.network.api.TmdbApiService
import com.example.cinemaxapp.core.data.network.model.TmdbGenreDto
import com.example.cinemaxapp.core.data.network.model.TmdbMovieDto
import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory

// DTO → Entity Mappers   (Network layer → Database layer)
// Called by MovieRepositoryImpl after a successful TMDB API response.


fun TmdbMovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id           = this.id,
        title        = this.title,
        backdropPath = this.backdropPath,
        posterPath   = this.posterPath,
        releaseDate  = this.releaseDate,
        voteAverage  = this.voteAverage,
    )
}


fun TmdbMovieDto.toNowPlayingRef(): NowPlayingMovieRef {
    return NowPlayingMovieRef(movieId = this.id)
}


fun TmdbMovieDto.toCrossRef(genreId: Int): MovieGenreCrossRef {
    return MovieGenreCrossRef(
        movieId = this.id,
        genreId = genreId,
    )
}


fun TmdbGenreDto.toGenreEntity(): GenreEntity {
    return GenreEntity(
        id   = this.id,
        name = this.name,
    )
}



// Entity → Domain Model Mappers   (Database layer → Domain/UI layer)
// Called by MovieRepositoryImpl when reading from Room and building domain models.

fun MovieEntity.toFeaturedBanner(): FeaturedBanner {
    val imageUrl = when {
        !this.backdropPath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W780 + this.backdropPath
        !this.posterPath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W500 + this.posterPath
        else ->
            "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=800&auto=format&fit=crop&q=80"
    }
    return FeaturedBanner(
        id              = this.id.toString(),
        title           = this.title,
        releaseDateText = formatReleaseDateText(this.releaseDate),
        bannerImageUrl  = imageUrl,
    )
}


fun MovieEntity.toMovie(): Movie {
    val posterUrl = when {
        !this.posterPath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W500 + this.posterPath
        else ->
            "https://images.unsplash.com/photo-1635805737707-575885ab0820?w=600&auto=format&fit=crop&q=80"
    }
    return Movie(
        id          = this.id.toString(),
        title       = this.title,
        posterUrl   = posterUrl,
        rating      = this.voteAverage ?: 4.5,
        category    = "Action",
        releaseDate = this.releaseDate ?: "",
    )
}


fun GenreEntity.toMovieCategory(): MovieCategory {
    return MovieCategory(
        id   = this.id.toString(),
        name = this.name,
    )
}



// Private Helpers

private fun formatReleaseDateText(rawDate: String?): String {
    if (rawDate.isNullOrBlank()) return ""
    return try {
        val parts = rawDate.split("-")
        if (parts.size == 3) {
            val year  = parts[0]
            val month = when (parts[1]) {
                "01" -> "January"; "02" -> "February"; "03" -> "March"
                "04" -> "April";   "05" -> "May";      "06" -> "June"
                "07" -> "July";    "08" -> "August";   "09" -> "September"
                "10" -> "October"; "11" -> "November"; "12" -> "December"
                else -> parts[1]
            }
            val day = parts[2].toIntOrNull() ?: 1
            "On $month $day, $year"
        } else rawDate
    } catch (e: Exception) {
        rawDate
    }
}
