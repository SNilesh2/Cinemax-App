package com.example.cinemaxapp.core.domain.usecase;

import com.example.cinemaxapp.core.domain.repository.MovieRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SyncMovieDetailsUseCase_Factory implements Factory<SyncMovieDetailsUseCase> {
  private final Provider<MovieRepository> movieRepositoryProvider;

  public SyncMovieDetailsUseCase_Factory(Provider<MovieRepository> movieRepositoryProvider) {
    this.movieRepositoryProvider = movieRepositoryProvider;
  }

  @Override
  public SyncMovieDetailsUseCase get() {
    return newInstance(movieRepositoryProvider.get());
  }

  public static SyncMovieDetailsUseCase_Factory create(
      Provider<MovieRepository> movieRepositoryProvider) {
    return new SyncMovieDetailsUseCase_Factory(movieRepositoryProvider);
  }

  public static SyncMovieDetailsUseCase newInstance(MovieRepository movieRepository) {
    return new SyncMovieDetailsUseCase(movieRepository);
  }
}
