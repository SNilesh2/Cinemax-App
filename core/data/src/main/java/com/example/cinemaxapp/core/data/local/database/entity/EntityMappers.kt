package com.example.cinemaxapp.core.data.local.database.entity


import com.example.cinemaxapp.core.data.network.api.TmdbApiService
import com.example.cinemaxapp.core.data.network.model.TmdbCastMemberDto
import com.example.cinemaxapp.core.data.network.model.TmdbCrewMemberDto
import com.example.cinemaxapp.core.data.network.model.TmdbGenreDto
import com.example.cinemaxapp.core.data.network.model.TmdbMovieDetailsDto
import com.example.cinemaxapp.core.data.network.model.TmdbMovieDto
import com.example.cinemaxapp.core.model.CreditPerson
import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory
import com.example.cinemaxapp.core.model.MovieDetails
import kotlin.collections.mapNotNull
import kotlin.text.get
import kotlin.toString

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


// MOVIE DETAILS MAPPERS — added for the Movie Details feature

val RELEVANT_CREW_JOBS = setOf(
    "Director",
    "Screenplay",
    "Writer",
    "Director of Photography",
    "Original Music Composer",
    "Producer",
)


// DTO → Entity Mappers for Movie Details


fun TmdbMovieDetailsDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id           = this.id,
        title        = this.title,
        backdropPath = this.backdropPath,
        posterPath   = this.posterPath,
        releaseDate  = this.releaseDate,
        voteAverage  = this.voteAverage,
        overview     = this.overview,
        runtime      = this.runtime,
        tagline      = this.tagline,
        homepage     = this.homepage,
    )
}


fun TmdbCastMemberDto.toCreditEntity(): CreditEntity {
    return CreditEntity(
        personId    = this.id,
        name        = this.name,
        profilePath = this.profilePath,
    )
}


fun TmdbCrewMemberDto.toCreditEntity(): CreditEntity {
    return CreditEntity(
        personId    = this.id,
        name        = this.name,
        profilePath = this.profilePath,
    )
}


fun TmdbCastMemberDto.toMovieCreditRef(movieId: Int): MovieCreditRef {
    return MovieCreditRef(
        creditId   = this.creditId,
        movieId    = movieId,
        personId   = this.id,
        creditType = "cast",
        character  = this.character,
        job        = null,
        department = null,
        castOrder  = this.order,
    )
}


fun TmdbCrewMemberDto.toMovieCreditRef(movieId: Int): MovieCreditRef {
    return MovieCreditRef(
        creditId   = this.creditId,
        movieId    = movieId,
        personId   = this.id,
        creditType = "crew",
        character  = null,
        job        = this.job,
        department = this.department,
        castOrder  = 0,
    )
}


// Entity → Domain Model Mappers for Movie Details


fun CreditEntity.toCreditPerson(ref: MovieCreditRef): CreditPerson {
    val profileUrl = when {
        !this.profilePath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W185 + this.profilePath
        else -> ""
    }
    val role = when (ref.creditType) {
        "cast" -> ref.character?.takeIf { it.isNotBlank() } ?: this.name
        else   -> ref.job?.takeIf { it.isNotBlank() }
            ?: ref.department?.takeIf { it.isNotBlank() }
            ?: ""
    }
    return CreditPerson(
        id         = this.personId.toString(),
        name       = this.name,
        role       = role,
        profileUrl = profileUrl,
    )
}


fun MovieWithCredits.toMovieDetails(
    creditRefs: List<MovieCreditRef>,
    genreEntities: List<GenreEntity> = emptyList(),
): MovieDetails {
    val movie = this.movie

    // Build a lookup map: personId → MovieCreditRef for fast joining
    val refByCreditId = creditRefs.associateBy { it.creditId }

    // Build a lookup for person entities: personId → CreditEntity
    val personById = this.credits.associateBy { it.personId }

    // Cast: all cast refs, sorted by castOrder ascending
    val cast = creditRefs
        .filter { it.creditType == "cast" }
        .sortedBy { it.castOrder }
        .mapNotNull { ref ->
            personById[ref.personId]?.toCreditPerson(ref)
        }

    // Crew: only relevant jobs, in order returned by TMDB
    val crew = creditRefs
        .filter { it.creditType == "crew" && it.job in RELEVANT_CREW_JOBS }
        .mapNotNull { ref ->
            personById[ref.personId]?.toCreditPerson(ref)
        }

    // Image URLs
    val posterUrl = when {
        !movie.posterPath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W500 + movie.posterPath
        else -> ""
    }
    val backdropUrl = when {
        !movie.backdropPath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W780 + movie.backdropPath
        !movie.posterPath.isNullOrBlank() ->
            TmdbApiService.IMAGE_BASE_URL_W500 + movie.posterPath
        else -> ""
    }

    // Release year — extract just the year from "2021-12-15"
    val releaseYear = movie.releaseDate?.take(4) ?: ""

    // Runtime — format as "148 Minutes" or "—" if unknown
    val runtimeStr = movie.runtime?.let { min ->
        if (min > 0) "$min Minutes" else "—"
    } ?: "—"

    // Rating — vote_average is on a 0–10 scale; round to 1 decimal for display
    val rating = movie.voteAverage ?: 0.0

    val genreNames = genreEntities.map { it.name }
    return MovieDetails(
        id          = movie.id.toString(),
        title       = movie.title,
        tagline     = movie.tagline.orEmpty(),
        posterUrl   = posterUrl,
        backdropUrl = backdropUrl,
        releaseYear = releaseYear,
        runtime     = runtimeStr,
        genres      = genreNames,
        rating      = rating,
        overview    = movie.overview.orEmpty(),
        homepage    = movie.homepage.orEmpty(),
        cast        = cast,
        crew        = crew,
    )
}
