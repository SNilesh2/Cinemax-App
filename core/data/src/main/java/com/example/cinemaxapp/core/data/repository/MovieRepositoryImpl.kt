package com.example.cinemaxapp.core.data.repository

import android.util.Log
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao
import com.example.cinemaxapp.core.data.local.database.entity.MovieGenreCrossRef
import com.example.cinemaxapp.core.data.local.database.entity.RELEVANT_CREW_JOBS
import com.example.cinemaxapp.core.data.local.database.entity.toCreditEntity
import com.example.cinemaxapp.core.data.local.database.entity.toCrossRef
import com.example.cinemaxapp.core.data.local.database.entity.toFeaturedBanner
import com.example.cinemaxapp.core.data.local.database.entity.toGenreEntity
import com.example.cinemaxapp.core.data.local.database.entity.toMovie
import com.example.cinemaxapp.core.data.local.database.entity.toMovieCategory
import com.example.cinemaxapp.core.data.local.database.entity.toMovieCreditRef
import com.example.cinemaxapp.core.data.local.database.entity.toMovieDetails
import com.example.cinemaxapp.core.data.local.database.entity.toMovieEntity
import com.example.cinemaxapp.core.data.local.database.entity.toNowPlayingRef
import com.example.cinemaxapp.core.data.network.api.TmdbApiService
import com.example.cinemaxapp.core.domain.repository.MovieRepository
import com.example.cinemaxapp.core.model.FeaturedBanner
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.MovieCategory
import com.example.cinemaxapp.core.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val tmdbApiService: TmdbApiService,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val creditDao: CreditDao,
) : MovieRepository {

    // JOB 1: SERVE THE UI — Read from Room (source of truth)

    override fun getFeaturedBanners(): Flow<List<FeaturedBanner>> {
        return movieDao.getNowPlayingMovies().map { entities ->
            entities.map { it.toFeaturedBanner() }
        }
    }


    override fun getMovieCategories(): Flow<List<MovieCategory>> {
        return genreDao.getAll().map { entities ->
            val allCategory = MovieCategory(id = "0", name = "All")
            val tmdbCategories = entities.map { it.toMovieCategory() }
            listOf(allCategory) + tmdbCategories
        }
    }



    override fun getPopularMovies(genreId: String): Flow<List<Movie>> {
        return if (genreId == "0" || genreId.isBlank()) {
            // "All" tab: show Now Playing movies (unchanged)
            movieDao.getNowPlayingMovies().map { entities ->
                entities.map { it.toMovie() }
            }
        } else {
            // Specific genre: use @Relation-based GenreWithMovies query.
            // Room internally runs two queries wrapped in a transaction:
            //   1. SELECT * FROM genres WHERE id = :genreId
            //   2. SELECT movies joined via movie_genre_cross_ref WHERE genre_id = <id>
            // The result is GenreWithMovies? (nullable if the genre isn't cached yet).
            // We extract .movies from it and map to domain Movie models.
            val genreIdInt = genreId.toIntOrNull() ?: 0
            movieDao.getGenreWithMovies(genreIdInt).map { genreWithMovies ->
                // genreWithMovies is null when the genres table hasn't been populated yet
                // (e.g. first install before syncGenres() completes). Return empty list.
                genreWithMovies?.movies?.map { it.toMovie() } ?: emptyList()
            }
        }
    }

    // ═══════════════════════════════════════════════════════════
    // JOB 2: KEEP THE DATABASE FRESH — Sync from TMDB in the background
    // ═══════════════════════════════════════════════════════════


    override suspend fun syncNowPlayingMovies() {
        withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "syncNowPlayingMovies: fetching from TMDB Now Playing API...")
                val movies = tmdbApiService.getNowPlayingMovies().results ?: emptyList()
                Log.d(TAG, "syncNowPlayingMovies: received ${movies.size} movies")

                if (movies.isNotEmpty()) {
                    // Step 1: Upsert movie data into the movies table
                    val movieEntities = movies.map { it.toMovieEntity() }
                    movieDao.upsertMovies(movieEntities)

                    // Step 2: Replace Now Playing membership references
                    val refs = movies.map { it.toNowPlayingRef() }
                    movieDao.clearNowPlayingRefs()
                    movieDao.upsertNowPlayingRefs(refs)

                    Log.d(TAG, "syncNowPlayingMovies: saved ${movies.size} movies to Room")
                }
            } catch (e: Exception) {
                Log.e(TAG, "syncNowPlayingMovies: failed — ${e.message}")
            }
        }
    }


    override suspend fun syncGenres() {
        withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "syncGenres: fetching from TMDB Genre API...")
                val genres = tmdbApiService.getMovieGenres().genres ?: emptyList()
                Log.d(TAG, "syncGenres: received ${genres.size} genres")

                if (genres.isNotEmpty()) {
                    val entities = genres.map { it.toGenreEntity() }
                    genreDao.clearAll()
                    genreDao.upsertAll(entities)
                    Log.d(TAG, "syncGenres: saved ${genres.size} genres to Room")
                }
            } catch (e: Exception) {
                Log.e(TAG, "syncGenres: failed — ${e.message}")
            }
        }
    }


    override suspend fun syncMoviesForGenre(genreId: String) {
        withContext(Dispatchers.IO) {
            try {
                val genreIdInt = genreId.toIntOrNull() ?: return@withContext
                Log.d(TAG, "syncMoviesForGenre: fetching genre=$genreId from TMDB Discover API...")
                val movies = tmdbApiService.discoverMovies(withGenres = genreId).results ?: emptyList()
                Log.d(TAG, "syncMoviesForGenre: received ${movies.size} movies for genre=$genreId")

                if (movies.isNotEmpty()) {
                    // Step 1: Upsert movie data (no duplicates if movie already exists)
                    val movieEntities = movies.map { it.toMovieEntity() }
                    movieDao.upsertMovies(movieEntities)

                    // Step 2: Replace genre cross-references for this genre
                    val crossRefs = movies.map { it.toCrossRef(genreId = genreIdInt) }
                    movieDao.clearCrossRefsForGenre(genreIdInt)
                    movieDao.upsertCrossRefs(crossRefs)

                    Log.d(TAG, "syncMoviesForGenre: saved ${movies.size} movies for genre=$genreId")
                }
            } catch (e: Exception) {
                Log.e(TAG, "syncMoviesForGenre: failed for genre=$genreId — ${e.message}")
            }
        }
    }

    override suspend fun toggleWishlist(movieId: String): Boolean = true


    // JOB 1+2: MOVIE DETAILS (read + sync)

    override fun getMovieDetails(movieId: Int): Flow<MovieDetails?> {
        return combine(
            movieDao.getMovieWithGenres(movieId),
            creditDao.getMovieWithCredits(movieId),
            creditDao.getMovieCreditRefs(movieId),
        ) { movieWithGenres , movieWithCredits, creditRefs ->
            val genreEntities = movieWithGenres?.genres ?: emptyList()
            movieWithCredits?.toMovieDetails(
                creditRefs,
                genreEntities = genreEntities,
            )
        }
    }


    override suspend fun syncMovieDetails(movieId: Int) {
        withContext(Dispatchers.IO) {
            try {
                val dto = tmdbApiService.getMovieDetails(movieId)

                // 1. Upsert MovieEntity with full detail fields
                movieDao.upsertMovies(listOf(dto.toMovieEntity()))


                // 2. Upsert genre entities and cross-refs from the full genre list
                val genreEntities = dto.genres.map { it.toGenreEntity() }
                if(genreEntities.isNotEmpty())
                {
                    genreDao.upsertAll(genreEntities)
                }

                val crossRefs = dto.genres.map {
                    MovieGenreCrossRef(
                        movieId = dto.id,
                        genreId = it.id,
                    )
                }
                if (crossRefs.isNotEmpty()) {
                    movieDao.upsertCrossRefs(crossRefs)
                }

                // 3. Upsert CreditEntity rows (person info — deduplicated by personId PK)
                val castEntities = dto.credits?.cast?.map { it.toCreditEntity() } ?: emptyList()
                val crewEntities = dto.credits?.crew
                    ?.filter { it.job in RELEVANT_CREW_JOBS }
                    ?.map { it.toCreditEntity() } ?: emptyList()
                creditDao.upsertAll(castEntities + crewEntities)

                // 4. Clear old credit refs for this movie before inserting fresh set
                creditDao.clearCreditsForMovie(movieId)

                // 5. Upsert new MovieCreditRef rows
                val castRefs = dto.credits?.cast?.map { it.toMovieCreditRef(dto.id) } ?: emptyList()
                val crewRefs = dto.credits?.crew
                    ?.filter { it.job in RELEVANT_CREW_JOBS }
                    ?.map { it.toMovieCreditRef(dto.id) } ?: emptyList()
                creditDao.upsertMovieCreditRefs(castRefs + crewRefs)

                Log.d(TAG, "syncMovieDetails: complete for movie $movieId")
            } catch (e: Exception) {
                Log.e(TAG, "syncMovieDetails: failed for movie=$movieId — ${e.message}")
            }
        }
    }


    private companion object {
        const val TAG = "MovieRepositoryImpl"
    }
}
