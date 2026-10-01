package com.example.cinemaxapp.core.data.repository;

import com.example.cinemaxapp.core.data.local.database.dao.CreditDao;
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao;
import com.example.cinemaxapp.core.data.local.database.dao.SearchDao;
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
public final class SearchRepositoryImpl_Factory implements Factory<SearchRepositoryImpl> {
  private final Provider<TmdbApiService> apiProvider;

  private final Provider<SearchDao> searchDaoProvider;

  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<CreditDao> creditDaoProvider;

  public SearchRepositoryImpl_Factory(Provider<TmdbApiService> apiProvider,
      Provider<SearchDao> searchDaoProvider, Provider<MovieDao> movieDaoProvider,
      Provider<CreditDao> creditDaoProvider) {
    this.apiProvider = apiProvider;
    this.searchDaoProvider = searchDaoProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.creditDaoProvider = creditDaoProvider;
  }

  @Override
  public SearchRepositoryImpl get() {
    return newInstance(apiProvider.get(), searchDaoProvider.get(), movieDaoProvider.get(), creditDaoProvider.get());
  }

  public static SearchRepositoryImpl_Factory create(Provider<TmdbApiService> apiProvider,
      Provider<SearchDao> searchDaoProvider, Provider<MovieDao> movieDaoProvider,
      Provider<CreditDao> creditDaoProvider) {
    return new SearchRepositoryImpl_Factory(apiProvider, searchDaoProvider, movieDaoProvider, creditDaoProvider);
  }

  public static SearchRepositoryImpl newInstance(TmdbApiService api, SearchDao searchDao,
      MovieDao movieDao, CreditDao creditDao) {
    return new SearchRepositoryImpl(api, searchDao, movieDao, creditDao);
  }
}
