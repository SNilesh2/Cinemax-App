package com.example.cinemaxapp.core.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.cinemaxapp.core.data.local.database.entity.CreditEntity
import com.example.cinemaxapp.core.data.local.database.entity.MovieCreditRef
import com.example.cinemaxapp.core.data.local.database.entity.MovieWithCredits
import kotlinx.coroutines.flow.Flow



@Dao
interface CreditDao {

    // ─── credits table ─────────────────────────────────────────────────────

    @Upsert
    suspend fun upsertAll(credits: List<CreditEntity>)

    // ─── movie_credit_ref table ─────────────────────────────────────────────
    @Upsert
    suspend fun upsertMovieCreditRefs(refs: List<MovieCreditRef>)


    @Query("DELETE FROM movie_credit_ref WHERE movie_id = :movieId")
    suspend fun clearCreditsForMovie(movieId: Int)


    @Query("SELECT * FROM movie_credit_ref WHERE movie_id = :movieId")
    fun getMovieCreditRefs(movieId: Int): Flow<List<MovieCreditRef>>


    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieWithCredits(movieId: Int): Flow<MovieWithCredits?>
}
