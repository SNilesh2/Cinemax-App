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
public final class ToggleWishlistUseCase_Factory implements Factory<ToggleWishlistUseCase> {
  private final Provider<MovieRepository> movieRepositoryProvider;

  public ToggleWishlistUseCase_Factory(Provider<MovieRepository> movieRepositoryProvider) {
    this.movieRepositoryProvider = movieRepositoryProvider;
  }

  @Override
  public ToggleWishlistUseCase get() {
    return newInstance(movieRepositoryProvider.get());
  }

  public static ToggleWishlistUseCase_Factory create(
      Provider<MovieRepository> movieRepositoryProvider) {
    return new ToggleWishlistUseCase_Factory(movieRepositoryProvider);
  }

  public static ToggleWishlistUseCase newInstance(MovieRepository movieRepository) {
    return new ToggleWishlistUseCase(movieRepository);
  }
}
