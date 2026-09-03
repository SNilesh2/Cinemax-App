package com.example.cinemaxapp.feature.home;

import com.example.cinemaxapp.core.domain.usecase.GetFeaturedBannersUseCase;
import com.example.cinemaxapp.core.domain.usecase.GetMovieCategoriesUseCase;
import com.example.cinemaxapp.core.domain.usecase.GetPopularMoviesUseCase;
import com.example.cinemaxapp.core.domain.usecase.SyncGenresUseCase;
import com.example.cinemaxapp.core.domain.usecase.SyncMoviesForGenreUseCase;
import com.example.cinemaxapp.core.domain.usecase.SyncNowPlayingMoviesUseCase;
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

  private final Provider<SyncNowPlayingMoviesUseCase> syncNowPlayingMoviesUseCaseProvider;

  private final Provider<SyncGenresUseCase> syncGenresUseCaseProvider;

  private final Provider<SyncMoviesForGenreUseCase> syncMoviesForGenreUseCaseProvider;

  public HomeViewModel_Factory(
      Provider<GetFeaturedBannersUseCase> getFeaturedBannersUseCaseProvider,
      Provider<GetMovieCategoriesUseCase> getMovieCategoriesUseCaseProvider,
      Provider<GetPopularMoviesUseCase> getPopularMoviesUseCaseProvider,
      Provider<SyncNowPlayingMoviesUseCase> syncNowPlayingMoviesUseCaseProvider,
      Provider<SyncGenresUseCase> syncGenresUseCaseProvider,
      Provider<SyncMoviesForGenreUseCase> syncMoviesForGenreUseCaseProvider) {
    this.getFeaturedBannersUseCaseProvider = getFeaturedBannersUseCaseProvider;
    this.getMovieCategoriesUseCaseProvider = getMovieCategoriesUseCaseProvider;
    this.getPopularMoviesUseCaseProvider = getPopularMoviesUseCaseProvider;
    this.syncNowPlayingMoviesUseCaseProvider = syncNowPlayingMoviesUseCaseProvider;
    this.syncGenresUseCaseProvider = syncGenresUseCaseProvider;
    this.syncMoviesForGenreUseCaseProvider = syncMoviesForGenreUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getFeaturedBannersUseCaseProvider.get(), getMovieCategoriesUseCaseProvider.get(), getPopularMoviesUseCaseProvider.get(), syncNowPlayingMoviesUseCaseProvider.get(), syncGenresUseCaseProvider.get(), syncMoviesForGenreUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetFeaturedBannersUseCase> getFeaturedBannersUseCaseProvider,
      Provider<GetMovieCategoriesUseCase> getMovieCategoriesUseCaseProvider,
      Provider<GetPopularMoviesUseCase> getPopularMoviesUseCaseProvider,
      Provider<SyncNowPlayingMoviesUseCase> syncNowPlayingMoviesUseCaseProvider,
      Provider<SyncGenresUseCase> syncGenresUseCaseProvider,
      Provider<SyncMoviesForGenreUseCase> syncMoviesForGenreUseCaseProvider) {
    return new HomeViewModel_Factory(getFeaturedBannersUseCaseProvider, getMovieCategoriesUseCaseProvider, getPopularMoviesUseCaseProvider, syncNowPlayingMoviesUseCaseProvider, syncGenresUseCaseProvider, syncMoviesForGenreUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetFeaturedBannersUseCase getFeaturedBannersUseCase,
      GetMovieCategoriesUseCase getMovieCategoriesUseCase,
      GetPopularMoviesUseCase getPopularMoviesUseCase,
      SyncNowPlayingMoviesUseCase syncNowPlayingMoviesUseCase, SyncGenresUseCase syncGenresUseCase,
      SyncMoviesForGenreUseCase syncMoviesForGenreUseCase) {
    return new HomeViewModel(getFeaturedBannersUseCase, getMovieCategoriesUseCase, getPopularMoviesUseCase, syncNowPlayingMoviesUseCase, syncGenresUseCase, syncMoviesForGenreUseCase);
  }
}
