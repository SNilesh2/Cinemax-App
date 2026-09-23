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
public final class GetWishlistMoviesUseCase_Factory implements Factory<GetWishlistMoviesUseCase> {
  private final Provider<MovieRepository> movieRepositoryProvider;

  public GetWishlistMoviesUseCase_Factory(Provider<MovieRepository> movieRepositoryProvider) {
    this.movieRepositoryProvider = movieRepositoryProvider;
  }

  @Override
  public GetWishlistMoviesUseCase get() {
    return newInstance(movieRepositoryProvider.get());
  }

  public static GetWishlistMoviesUseCase_Factory create(
      Provider<MovieRepository> movieRepositoryProvider) {
    return new GetWishlistMoviesUseCase_Factory(movieRepositoryProvider);
  }

  public static GetWishlistMoviesUseCase newInstance(MovieRepository movieRepository) {
    return new GetWishlistMoviesUseCase(movieRepository);
  }
}
