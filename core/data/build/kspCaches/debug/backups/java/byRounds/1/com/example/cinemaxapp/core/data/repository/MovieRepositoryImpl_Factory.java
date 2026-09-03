package com.example.cinemaxapp.core.data.repository;

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

  public MovieRepositoryImpl_Factory(Provider<TmdbApiService> tmdbApiServiceProvider,
      Provider<MovieDao> movieDaoProvider, Provider<GenreDao> genreDaoProvider) {
    this.tmdbApiServiceProvider = tmdbApiServiceProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.genreDaoProvider = genreDaoProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(tmdbApiServiceProvider.get(), movieDaoProvider.get(), genreDaoProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<TmdbApiService> tmdbApiServiceProvider,
      Provider<MovieDao> movieDaoProvider, Provider<GenreDao> genreDaoProvider) {
    return new MovieRepositoryImpl_Factory(tmdbApiServiceProvider, movieDaoProvider, genreDaoProvider);
  }

  public static MovieRepositoryImpl newInstance(TmdbApiService tmdbApiService, MovieDao movieDao,
      GenreDao genreDao) {
    return new MovieRepositoryImpl(tmdbApiService, movieDao, genreDao);
  }
}
