package com.example.cinemaxapp.core.data.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.cinemaxapp.core.data.local.database.entity.CreditEntity
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity
import com.example.cinemaxapp.core.data.local.database.entity.SearchMovieRef
import com.example.cinemaxapp.core.data.local.database.entity.SearchPersonRef
import com.example.cinemaxapp.core.data.local.database.entity.SearchRemoteKeyEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchDao {

    // ─── SearchMovieRef Operations ──────────────────────────────────────────
    @Upsert
    suspend fun upsertMovieRefs(refs: List<SearchMovieRef>)

    @Query("DELETE FROM search_movies_ref WHERE `query` = :query")
    suspend fun clearMovieRefsForQuery(query: String)


    @Query(
        """
        SELECT m.* FROM movies m
        INNER JOIN search_movies_ref r ON m.id = r.movie_id
        WHERE r.`query` = :query
        ORDER BY r.page ASC, r.position ASC
        """
    )
    fun getMoviesForQueryPaging(query: String): PagingSource<Int, MovieEntity>

    // ─── SearchPersonRef Operations & Person Query ──────────────────────────

    @Upsert
    suspend fun upsertPersonRefs(refs: List<SearchPersonRef>)

    @Query("DELETE FROM search_persons_ref WHERE `query` = :query")
    suspend fun clearPersonRefsForQuery(query: String)


    @Query(
        """
        SELECT c.* FROM credits c
        INNER JOIN search_persons_ref r ON c.person_id = r.person_id
        WHERE r.`query` = :query
        ORDER BY r.page ASC, r.position ASC
        """
    )
    fun getPersonsForQuery(query: String): Flow<List<CreditEntity>>

    // ─── Movies & Credits Backing Table Operations ──────────────────────────

    @Upsert
    suspend fun upsertMovies(movies: List<MovieEntity>)

    @Upsert
    suspend fun upsertCredits(credits: List<CreditEntity>)

    // ─── SearchRemoteKeyEntity Operations ───────────────────────────────────

    @Upsert
    suspend fun upsertRemoteKey(remoteKey: SearchRemoteKeyEntity)

    @Query("SELECT * FROM search_remote_keys WHERE `query` = :query")
    suspend fun getRemoteKeyForQuery(query: String): SearchRemoteKeyEntity?

    @Query("DELETE FROM search_remote_keys WHERE `query` = :query")
    suspend fun clearRemoteKeyForQuery(query: String)

    // ─── Transaction Helper ────────────────────────────────────────────────
    @Transaction
    suspend fun clearSearchCacheForQuery(query: String) {
        clearMovieRefsForQuery(query)
        clearPersonRefsForQuery(query)
        clearRemoteKeyForQuery(query)
    }
}