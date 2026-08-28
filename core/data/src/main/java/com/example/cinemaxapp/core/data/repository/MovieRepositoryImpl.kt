package com.example.cinemaxapp.core.data.repository

import android.util.Log
import com.example.cinemaxapp.core.data.network.api.TmdbApiService
import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.toString


@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val tmdbApiService: TmdbApiService,
) : MovieRepository {

    // ─── Fallback sample data (used when TMDB returns empty or is unreachable) ───

    private val sampleBanners = listOf(
        FeaturedBanner(
            id = "b1",
            title = "Black Panther: Wakanda\nForever",
            releaseDateText = "On March 2, 2022",
            bannerImageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=800&auto=format&fit=crop&q=80",
        ),
        FeaturedBanner(
            id = "b2",
            title = "Avatar: The Way of\nWater",
            releaseDateText = "On December 16, 2022",
            bannerImageUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=800&auto=format&fit=crop&q=80",
        ),
        FeaturedBanner(
            id = "b3",
            title = "The Batman",
            releaseDateText = "On March 4, 2022",
            bannerImageUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80",
        ),
    )

    private val samplePopularMovies = listOf(
        Movie(
            id = "m1",
            title = "Spider-Man No..",
            posterUrl = "https://images.unsplash.com/photo-1635805737707-575885ab0820?w=600&auto=format&fit=crop&q=80",
            rating = 4.5,
            category = "Action",
        ),
        Movie(
            id = "m2",
            title = "Life of PI",
            posterUrl = "https://images.unsplash.com/photo-1511447333015-45b65e60f6d5?w=600&auto=format&fit=crop&q=80",
            rating = 4.5,
            category = "Action",
        ),
        Movie(
            id = "m3",
            title = "Riverdale",
            posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=600&auto=format&fit=crop&q=80",
            rating = 4.5,
            category = "Action",
        ),
        Movie(
            id = "m4",
            title = "Avengers: Endgame",
            posterUrl = "https://images.unsplash.com/photo-1568832359672-e36cf5d74f54?w=600&auto=format&fit=crop&q=80",
            rating = 4.8,
            category = "Action",
        ),
    )

    // ─── Repository Methods ───────────────────────────────────────────────────────

    override suspend fun getFeaturedBanners(): List<FeaturedBanner> = withContext(Dispatchers.IO) {
        Log.d("MovieRepositoryImpl", "getFeaturedBanners: calling TMDB Now Playing API...")

        val apiMovies = tmdbApiService.getNowPlayingMovies().results

        if (!apiMovies.isNullOrEmpty()) {
            Log.d("MovieRepositoryImpl", "getFeaturedBanners: TMDB returned ${apiMovies.size} movies")
            apiMovies.map { dto ->
                // Prefer backdrop (wide image) for the carousel banner.
                // Fall back to poster if no backdrop is available.
                val imageUrl = when {
                    !dto.backdropPath.isNullOrBlank() ->
                        TmdbApiService.IMAGE_BASE_URL_W780 + dto.backdropPath
                    !dto.posterPath.isNullOrBlank() ->
                        TmdbApiService.IMAGE_BASE_URL_W500 + dto.posterPath
                    else ->
                        "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=800&auto=format&fit=crop&q=80"
                }
                FeaturedBanner(
                    id = dto.id.toString(),
                    title = dto.title,
                    releaseDateText = formatReleaseDateText(dto.releaseDate),
                    bannerImageUrl = imageUrl,
                )
            }
        } else {
            Log.w("MovieRepositoryImpl", "getFeaturedBanners: TMDB returned empty, using sample data")
            sampleBanners
        }
    }


    override suspend fun getMovieCategories(): List<MovieCategory> = withContext(Dispatchers.IO) {
        Log.d("MovieRepositoryImpl", "getMovieCategories: calling TMDB Genre API...")

        val genres = tmdbApiService.getMovieGenres().genres

        if (!genres.isNullOrEmpty()) {
            Log.d("MovieRepositoryImpl", "getMovieCategories: TMDB returned ${genres.size} genres")

            // Prepend the "All" category, then append all TMDB genres.
            // "All" is a UI filter — it is not sent to TMDB as a real genre.
            val allCategory = MovieCategory(id = "0", name = "All")
            val tmdbCategories = genres.map { dto ->
                MovieCategory(
                    id = dto.id.toString(),   // TMDB genre id (Int) → String for MovieCategory
                    name = dto.name,
                )
            }

            listOf(allCategory) + tmdbCategories
        } else {
            Log.w("MovieRepositoryImpl", "getMovieCategories: TMDB returned empty, using fallback")
            // Fallback: a minimal list so the UI doesn't break if the genre API fails.
            listOf(
                MovieCategory("0",  "All"),
                MovieCategory("28", "Action"),
                MovieCategory("35", "Comedy"),
                MovieCategory("16", "Animation"),
                MovieCategory("99", "Documentary"),
                MovieCategory("18", "Drama"),
                MovieCategory("878", "Science Fiction"),
            )
        }
    }




    override suspend fun getPopularMovies(genreId: String): List<Movie> = withContext(Dispatchers.IO) {
        val isAllGenres = genreId.isBlank() || genreId == "0"

        val apiMovies = if (isAllGenres) {
            Log.d("MovieRepositoryImpl", "getPopularMovies: fetching Now Playing (all genres)")
            tmdbApiService.getNowPlayingMovies().results
        } else {
            Log.d("MovieRepositoryImpl", "getPopularMovies: fetching Discover for genre ID=$genreId")
            tmdbApiService.discoverMovies(withGenres = genreId).results
        }

        if (!apiMovies.isNullOrEmpty()) {
            Log.d("MovieRepositoryImpl", "getPopularMovies: received ${apiMovies.size} movies")
            apiMovies.map { dto ->
                val posterUrl = when {
                    !dto.posterPath.isNullOrBlank() ->
                        TmdbApiService.IMAGE_BASE_URL_W500 + dto.posterPath
                    else ->
                        "https://images.unsplash.com/photo-1635805737707-575885ab0820?w=600&auto=format&fit=crop&q=80"
                }
                Movie(
                    id = dto.id.toString(),
                    title = dto.title,
                    posterUrl = posterUrl,
                    rating = dto.voteAverage ?: 4.5,
                    category = "Action",
                    releaseDate = dto.releaseDate ?: "",
                )
            }
        } else {
            Log.w("MovieRepositoryImpl", "getPopularMovies: TMDB returned empty, using sample data")
            samplePopularMovies
        }
    }

    override suspend fun toggleWishlist(movieId: String): Boolean {
        return true
    }

    // ─── Private Helpers ──────────────────────────────────────────────────────────
    private fun formatReleaseDateText(rawDate: String?): String {
        if (rawDate.isNullOrBlank()) return "On March 2, 2022"
        return try {
            val parts = rawDate.split("-")
            if (parts.size == 3) {
                val year = parts[0]
                val month = when (parts[1]) {
                    "01" -> "January"; "02" -> "February"; "03" -> "March"
                    "04" -> "April";   "05" -> "May";      "06" -> "June"
                    "07" -> "July";    "08" -> "August";   "09" -> "September"
                    "10" -> "October"; "11" -> "November"; "12" -> "December"
                    else -> "March"
                }
                val day = parts[2].toIntOrNull() ?: 1
                "On $month $day, $year"
            } else {
                "On $rawDate"
            }
        } catch (e: Exception) {
            "On $rawDate"
        }
    }
}

