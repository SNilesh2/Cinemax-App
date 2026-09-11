package com.example.cinemaxapp.core.data.repository;

import com.example.cinemaxapp.core.data.local.database.dao.CreditDao;
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao;
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao;
import com.example.cinemaxapp.core.data.network.api.TmdbApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  private final Provider<TmdbApiService> tmdbApiServiceProvider;

  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<GenreDao> genreDaoProvider;

  private final Provider<CreditDao> creditDaoProvider;

  public MovieRepositoryImpl_Factory(Provider<TmdbApiService> tmdbApiServiceProvider,
      Provider<MovieDao> movieDaoProvider, Provider<GenreDao> genreDaoProvider,
      Provider<CreditDao> creditDaoProvider) {
    this.tmdbApiServiceProvider = tmdbApiServiceProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.genreDaoProvider = genreDaoProvider;
    this.creditDaoProvider = creditDaoProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(tmdbApiServiceProvider.get(), movieDaoProvider.get(), genreDaoProvider.get(), creditDaoProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<TmdbApiService> tmdbApiServiceProvider,
      Provider<MovieDao> movieDaoProvider, Provider<GenreDao> genreDaoProvider,
      Provider<CreditDao> creditDaoProvider) {
    return new MovieRepositoryImpl_Factory(tmdbApiServiceProvider, movieDaoProvider, genreDaoProvider, creditDaoProvider);
  }

  public static MovieRepositoryImpl newInstance(TmdbApiService tmdbApiService, MovieDao movieDao,
      GenreDao genreDao, CreditDao creditDao) {
    return new MovieRepositoryImpl(tmdbApiService, movieDao, genreDao, creditDao);
  }
}
