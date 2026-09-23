package com.example.cinemaxapp.core.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.cinemaxapp.core.data.local.database.entity.GenreWithMovies
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity
import com.example.cinemaxapp.core.data.local.database.entity.MovieGenreCrossRef
import com.example.cinemaxapp.core.data.local.database.entity.MovieWithGenres
import com.example.cinemaxapp.core.data.local.database.entity.NowPlayingMovieRef
import com.example.cinemaxapp.core.data.local.database.entity.WishlistMovieRef
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    // ─── movies table ─────────────────────────────────────────
    @Upsert
    suspend fun upsertMovies(movies: List<MovieEntity>)


    // ─── now_playing_movies_ref table ─────────────────────────
    @Query("DELETE FROM now_playing_movies_ref")
    suspend fun clearNowPlayingRefs()


    @Upsert
    suspend fun upsertNowPlayingRefs(refs: List<NowPlayingMovieRef>)


    @Query("""
        SELECT m.*
        FROM movies m
        INNER JOIN now_playing_movies_ref ref ON m.id = ref.movie_id
        ORDER BY m.id ASC
    """)
    fun getNowPlayingMovies(): Flow<List<MovieEntity>>


    // ─── movie_genre_cross_ref table ──────────────────────────
    @Query("DELETE FROM movie_genre_cross_ref WHERE genre_id = :genreId")
    suspend fun clearCrossRefsForGenre(genreId: Int)


    @Upsert
    suspend fun upsertCrossRefs(crossRefs: List<MovieGenreCrossRef>)


    @Transaction
    @Query("SELECT * FROM genres WHERE id = :genreId")
    fun getGenreWithMovies(genreId: Int): Flow<GenreWithMovies?>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieWithGenres(movieId: Int): Flow<MovieWithGenres?>


    // ─── wishlist_movies_ref table ────────────────────────────

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistRef(ref: WishlistMovieRef)


    @Query("DELETE FROM wishlist_movies_ref WHERE movie_id = :movieId")
    suspend fun deleteWishlistRef(movieId: Int)


    @Query("SELECT EXISTS(SELECT 1 FROM wishlist_movies_ref WHERE movie_id = :movieId)")
    fun isMovieWishlisted(movieId: Int): Flow<Boolean>

    /**
     * One-shot suspend check: used by toggleWishlist() to decide insert vs delete.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM wishlist_movies_ref WHERE movie_id = :movieId)")
    suspend fun isMovieWishlistedDirect(movieId: Int): Boolean


    @Transaction
    @Query("""
        SELECT m.* FROM movies m
        INNER JOIN wishlist_movies_ref ref ON m.id = ref.movie_id
        ORDER BY ref.added_at DESC
    """)
    fun getWishlistMoviesWithGenres(): Flow<List<MovieWithGenres>>

}
