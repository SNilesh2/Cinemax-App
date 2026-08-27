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
public final class GetMovieCategoriesUseCase_Factory implements Factory<GetMovieCategoriesUseCase> {
  private final Provider<MovieRepository> movieRepositoryProvider;

  public GetMovieCategoriesUseCase_Factory(Provider<MovieRepository> movieRepositoryProvider) {
    this.movieRepositoryProvider = movieRepositoryProvider;
  }

  @Override
  public GetMovieCategoriesUseCase get() {
    return newInstance(movieRepositoryProvider.get());
  }

  public static GetMovieCategoriesUseCase_Factory create(
      Provider<MovieRepository> movieRepositoryProvider) {
    return new GetMovieCategoriesUseCase_Factory(movieRepositoryProvider);
  }

  public static GetMovieCategoriesUseCase newInstance(MovieRepository movieRepository) {
    return new GetMovieCategoriesUseCase(movieRepository);
  }
}
