package com.example.cinemaxapp.feature.wishlist;

import com.example.cinemaxapp.core.domain.usecase.GetWishlistMoviesUseCase;
import com.example.cinemaxapp.core.domain.usecase.ToggleWishlistUseCase;
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
public final class WishlistViewModel_Factory implements Factory<WishlistViewModel> {
  private final Provider<GetWishlistMoviesUseCase> getWishlistMoviesUseCaseProvider;

  private final Provider<ToggleWishlistUseCase> toggleWishlistUseCaseProvider;

  public WishlistViewModel_Factory(
      Provider<GetWishlistMoviesUseCase> getWishlistMoviesUseCaseProvider,
      Provider<ToggleWishlistUseCase> toggleWishlistUseCaseProvider) {
    this.getWishlistMoviesUseCaseProvider = getWishlistMoviesUseCaseProvider;
    this.toggleWishlistUseCaseProvider = toggleWishlistUseCaseProvider;
  }

  @Override
  public WishlistViewModel get() {
    return newInstance(getWishlistMoviesUseCaseProvider.get(), toggleWishlistUseCaseProvider.get());
  }

  public static WishlistViewModel_Factory create(
      Provider<GetWishlistMoviesUseCase> getWishlistMoviesUseCaseProvider,
      Provider<ToggleWishlistUseCase> toggleWishlistUseCaseProvider) {
    return new WishlistViewModel_Factory(getWishlistMoviesUseCaseProvider, toggleWishlistUseCaseProvider);
  }

  public static WishlistViewModel newInstance(GetWishlistMoviesUseCase getWishlistMoviesUseCase,
      ToggleWishlistUseCase toggleWishlistUseCase) {
    return new WishlistViewModel(getWishlistMoviesUseCase, toggleWishlistUseCase);
  }
}
