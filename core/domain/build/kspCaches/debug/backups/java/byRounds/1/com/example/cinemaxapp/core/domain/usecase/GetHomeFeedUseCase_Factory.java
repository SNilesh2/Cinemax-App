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
public final class GetHomeFeedUseCase_Factory implements Factory<GetHomeFeedUseCase> {
  private final Provider<MovieRepository> movieRepositoryProvider;

  public GetHomeFeedUseCase_Factory(Provider<MovieRepository> movieRepositoryProvider) {
    this.movieRepositoryProvider = movieRepositoryProvider;
  }

  @Override
  public GetHomeFeedUseCase get() {
    return newInstance(movieRepositoryProvider.get());
  }

  public static GetHomeFeedUseCase_Factory create(
      Provider<MovieRepository> movieRepositoryProvider) {
    return new GetHomeFeedUseCase_Factory(movieRepositoryProvider);
  }

  public static GetHomeFeedUseCase newInstance(MovieRepository movieRepository) {
    return new GetHomeFeedUseCase(movieRepository);
  }
}
