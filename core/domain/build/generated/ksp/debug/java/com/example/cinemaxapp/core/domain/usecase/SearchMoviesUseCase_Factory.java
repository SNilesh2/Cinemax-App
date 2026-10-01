package com.example.cinemaxapp.core.domain.usecase;

import com.example.cinemaxapp.core.domain.repository.SearchRepository;
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
public final class SearchMoviesUseCase_Factory implements Factory<SearchMoviesUseCase> {
  private final Provider<SearchRepository> searchRepositoryProvider;

  public SearchMoviesUseCase_Factory(Provider<SearchRepository> searchRepositoryProvider) {
    this.searchRepositoryProvider = searchRepositoryProvider;
  }

  @Override
  public SearchMoviesUseCase get() {
    return newInstance(searchRepositoryProvider.get());
  }

  public static SearchMoviesUseCase_Factory create(
      Provider<SearchRepository> searchRepositoryProvider) {
    return new SearchMoviesUseCase_Factory(searchRepositoryProvider);
  }

  public static SearchMoviesUseCase newInstance(SearchRepository searchRepository) {
    return new SearchMoviesUseCase(searchRepository);
  }
}
