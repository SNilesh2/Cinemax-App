package com.example.cinemaxapp.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao
import com.example.cinemaxapp.core.data.local.database.dao.SearchDao
import com.example.cinemaxapp.core.data.local.database.entity.toMovie
import com.example.cinemaxapp.core.data.local.database.entity.toSearchPerson
import com.example.cinemaxapp.core.data.network.api.TmdbApiService
import com.example.cinemaxapp.core.domain.repository.SearchRepository
import com.example.cinemaxapp.core.model.Movie
import com.example.cinemaxapp.core.model.SearchPerson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val api: TmdbApiService,
    private val searchDao: SearchDao,
    private val movieDao: MovieDao,
    private val creditDao: CreditDao,
) : SearchRepository {


    @OptIn(ExperimentalPagingApi::class)
    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize         = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = false,
            ),
            remoteMediator = SearchRemoteMediator(
                query     = query,
                api       = api,
                searchDao = searchDao,
                movieDao  = movieDao,
                creditDao = creditDao,
            ),
            pagingSourceFactory = {
                searchDao.getMoviesForQueryPaging(query)
            },
        ).flow
            .map { pagingData ->
                pagingData.map { movieWithGenres -> movieWithGenres.toMovie() }
            }
    }


    override fun getSearchPersons(query: String): Flow<List<SearchPerson>> {
        return searchDao.getPersonsForQuery(query)
            .map { credits -> credits.map { it.toSearchPerson() } }
    }

    private companion object {
        const val PAGE_SIZE         = 20
        const val PREFETCH_DISTANCE = 5
    }
}
