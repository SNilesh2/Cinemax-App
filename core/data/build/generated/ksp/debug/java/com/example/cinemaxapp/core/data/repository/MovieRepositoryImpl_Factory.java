package com.example.cinemaxapp.core.data.repository;

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

  public MovieRepositoryImpl_Factory(Provider<TmdbApiService> tmdbApiServiceProvider) {
    this.tmdbApiServiceProvider = tmdbApiServiceProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(tmdbApiServiceProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(
      Provider<TmdbApiService> tmdbApiServiceProvider) {
    return new MovieRepositoryImpl_Factory(tmdbApiServiceProvider);
  }

  public static MovieRepositoryImpl newInstance(TmdbApiService tmdbApiService) {
    return new MovieRepositoryImpl(tmdbApiService);
  }
}
