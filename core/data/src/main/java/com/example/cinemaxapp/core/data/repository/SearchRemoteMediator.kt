package com.example.cinemaxapp.core.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao
import com.example.cinemaxapp.core.data.local.database.dao.SearchDao
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity
import com.example.cinemaxapp.core.data.local.database.entity.SearchRemoteKeyEntity
import com.example.cinemaxapp.core.data.local.database.entity.toCreditEntity
import com.example.cinemaxapp.core.data.local.database.entity.toMovieEntity
import com.example.cinemaxapp.core.data.local.database.entity.toSearchMovieRef
import com.example.cinemaxapp.core.data.local.database.entity.toSearchPersonRef
import com.example.cinemaxapp.core.data.network.api.TmdbApiService

@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator(
    private val query: String,
    private val api: TmdbApiService,
    private val searchDao: SearchDao,
    private val movieDao: MovieDao,
    private val creditDao: CreditDao,
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> STARTING_PAGE

                LoadType.PREPEND -> {
                    // Paging 3 only scrolls downward (APPEND). Prepend is never needed.
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    // Read the next page number stored in Room from the previous API call.
                    val remoteKey = searchDao.getRemoteKeyForQuery(query)
                    remoteKey?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            Log.d(TAG, "load: query='$query', page=$page, loadType=$loadType")

            // ── Network call ──────────────────────────────────────────────────
            val response = api.searchMutli(query = query,page = page)
            val results  = response.results ?: emptyList()
            val totalPages = response.totalPages ?: 1

            // Filter out TV results — search is movies + persons only.
            val movieResults  = results.filter { it.mediaType == MEDIA_TYPE_MOVIE }
            val personResults = results.filter { it.mediaType == MEDIA_TYPE_PERSON }

            Log.d(
                TAG,
                "load: page=$page → ${movieResults.size} movies, ${personResults.size} persons "
            )

            // ── Write to Room (in a logical sequence) ─────────────────────────
            // On REFRESH: clear old cache for this query before writing fresh data.
            if (loadType == LoadType.REFRESH) {
                searchDao.clearSearchCacheForQuery(query)
                Log.d(TAG, "load: cleared old search cache for query='$query'")
            }

            // 1. Persist MovieEntity rows via canonical MovieDao.
            val movieEntities = movieResults.map { it.toMovieEntity() }
            if (movieEntities.isNotEmpty()) {
                movieDao.upsertMovies(movieEntities)
            }

            // 2. Persist CreditEntity rows via canonical CreditDao.
            val creditEntities = personResults.map { it.toCreditEntity() }
            if (creditEntities.isNotEmpty()) {
                creditDao.upsertAll(creditEntities)
            }

            // 3. Write search membership refs (with page + position for ordering).
            val movieRefs = movieResults.mapIndexed { index, dto ->
                dto.toSearchMovieRef(query = query, page = page, position = index)
            }
            val personRefs = personResults.mapIndexed { index, dto ->
                dto.toSearchPersonRef(query = query, page = page, position = index)
            }
            if (movieRefs.isNotEmpty())  searchDao.upsertMovieRefs(movieRefs)
            if (personRefs.isNotEmpty()) searchDao.upsertPersonRefs(personRefs)

            // 4. Update remote key: next page = null when we've reached the last page.
            val nextPage = if (page >= totalPages) null else page + 1
            searchDao.upsertRemoteKey(
                SearchRemoteKeyEntity(
                    query = query,
                    nextPage = nextPage,
                    prevPage = if (page == STARTING_PAGE) null else page - 1,
                )
            )

            val endOfPagination = nextPage == null
            Log.d(TAG, "load: wrote page=$page for query='$query'. endOfPagination=$endOfPagination")
            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        }catch (e: Exception) {
            Log.e(TAG, "load: failed for query='$query' — ${e.message}")
            MediatorResult.Error(e)
        }
    }

    private companion object {
        const val TAG          = "SearchRemoteMediator"
        const val STARTING_PAGE = 1
        const val MEDIA_TYPE_MOVIE  = "movie"
        const val MEDIA_TYPE_PERSON = "person"
    }
}