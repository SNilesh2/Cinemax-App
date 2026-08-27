package com.example.cinemaxapp.feature.home;

import com.example.cinemaxapp.core.domain.usecase.GetFeaturedBannersUseCase;
import com.example.cinemaxapp.core.domain.usecase.GetMovieCategoriesUseCase;
import com.example.cinemaxapp.core.domain.usecase.GetPopularMoviesUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetFeaturedBannersUseCase> getFeaturedBannersUseCaseProvider;

  private final Provider<GetMovieCategoriesUseCase> getMovieCategoriesUseCaseProvider;

  private final Provider<GetPopularMoviesUseCase> getPopularMoviesUseCaseProvider;

  public HomeViewModel_Factory(
      Provider<GetFeaturedBannersUseCase> getFeaturedBannersUseCaseProvider,
      Provider<GetMovieCategoriesUseCase> getMovieCategoriesUseCaseProvider,
      Provider<GetPopularMoviesUseCase> getPopularMoviesUseCaseProvider) {
    this.getFeaturedBannersUseCaseProvider = getFeaturedBannersUseCaseProvider;
    this.getMovieCategoriesUseCaseProvider = getMovieCategoriesUseCaseProvider;
    this.getPopularMoviesUseCaseProvider = getPopularMoviesUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getFeaturedBannersUseCaseProvider.get(), getMovieCategoriesUseCaseProvider.get(), getPopularMoviesUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetFeaturedBannersUseCase> getFeaturedBannersUseCaseProvider,
      Provider<GetMovieCategoriesUseCase> getMovieCategoriesUseCaseProvider,
      Provider<GetPopularMoviesUseCase> getPopularMoviesUseCaseProvider) {
    return new HomeViewModel_Factory(getFeaturedBannersUseCaseProvider, getMovieCategoriesUseCaseProvider, getPopularMoviesUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetFeaturedBannersUseCase getFeaturedBannersUseCase,
      GetMovieCategoriesUseCase getMovieCategoriesUseCase,
      GetPopularMoviesUseCase getPopularMoviesUseCase) {
    return new HomeViewModel(getFeaturedBannersUseCase, getMovieCategoriesUseCase, getPopularMoviesUseCase);
  }
}
