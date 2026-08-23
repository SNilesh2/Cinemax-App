package com.example.cinemaxapp.feature.home;

import com.example.cinemaxapp.core.domain.usecase.GetHomeFeedUseCase;
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
  private final Provider<GetHomeFeedUseCase> getHomeFeedUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetHomeFeedUseCase> getHomeFeedUseCaseProvider) {
    this.getHomeFeedUseCaseProvider = getHomeFeedUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getHomeFeedUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetHomeFeedUseCase> getHomeFeedUseCaseProvider) {
    return new HomeViewModel_Factory(getHomeFeedUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetHomeFeedUseCase getHomeFeedUseCase) {
    return new HomeViewModel(getHomeFeedUseCase);
  }
}
