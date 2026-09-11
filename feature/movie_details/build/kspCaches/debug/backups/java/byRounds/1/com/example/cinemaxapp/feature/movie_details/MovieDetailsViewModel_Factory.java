package com.example.cinemaxapp.feature.movie_details;

import androidx.lifecycle.SavedStateHandle;
import com.example.cinemaxapp.core.domain.usecase.GetMovieDetailsUseCase;
import com.example.cinemaxapp.core.domain.usecase.SyncMovieDetailsUseCase;
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
public final class MovieDetailsViewModel_Factory implements Factory<MovieDetailsViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider;

  private final Provider<SyncMovieDetailsUseCase> syncMovieDetailsUseCaseProvider;

  public MovieDetailsViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider,
      Provider<SyncMovieDetailsUseCase> syncMovieDetailsUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getMovieDetailsUseCaseProvider = getMovieDetailsUseCaseProvider;
    this.syncMovieDetailsUseCaseProvider = syncMovieDetailsUseCaseProvider;
  }

  @Override
  public MovieDetailsViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getMovieDetailsUseCaseProvider.get(), syncMovieDetailsUseCaseProvider.get());
  }

  public static MovieDetailsViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetMovieDetailsUseCase> getMovieDetailsUseCaseProvider,
      Provider<SyncMovieDetailsUseCase> syncMovieDetailsUseCaseProvider) {
    return new MovieDetailsViewModel_Factory(savedStateHandleProvider, getMovieDetailsUseCaseProvider, syncMovieDetailsUseCaseProvider);
  }

  public static MovieDetailsViewModel newInstance(SavedStateHandle savedStateHandle,
      GetMovieDetailsUseCase getMovieDetailsUseCase,
      SyncMovieDetailsUseCase syncMovieDetailsUseCase) {
    return new MovieDetailsViewModel(savedStateHandle, getMovieDetailsUseCase, syncMovieDetailsUseCase);
  }
}
