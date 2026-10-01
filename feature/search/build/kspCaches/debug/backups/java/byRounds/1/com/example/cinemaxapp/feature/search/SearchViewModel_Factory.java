package com.example.cinemaxapp.feature.search;

import com.example.cinemaxapp.core.domain.usecase.GetSearchPersonsUseCase;
import com.example.cinemaxapp.core.domain.usecase.SearchMoviesUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SearchMoviesUseCase> searchMoviesUseCaseProvider;

  private final Provider<GetSearchPersonsUseCase> getSearchPersonsUseCaseProvider;

  public SearchViewModel_Factory(Provider<SearchMoviesUseCase> searchMoviesUseCaseProvider,
      Provider<GetSearchPersonsUseCase> getSearchPersonsUseCaseProvider) {
    this.searchMoviesUseCaseProvider = searchMoviesUseCaseProvider;
    this.getSearchPersonsUseCaseProvider = getSearchPersonsUseCaseProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(searchMoviesUseCaseProvider.get(), getSearchPersonsUseCaseProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<SearchMoviesUseCase> searchMoviesUseCaseProvider,
      Provider<GetSearchPersonsUseCase> getSearchPersonsUseCaseProvider) {
    return new SearchViewModel_Factory(searchMoviesUseCaseProvider, getSearchPersonsUseCaseProvider);
  }

  public static SearchViewModel newInstance(SearchMoviesUseCase searchMoviesUseCase,
      GetSearchPersonsUseCase getSearchPersonsUseCase) {
    return new SearchViewModel(searchMoviesUseCase, getSearchPersonsUseCase);
  }
}
