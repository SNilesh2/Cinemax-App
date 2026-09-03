package com.example.cinemaxapp.core.data.local.di;

import com.example.cinemaxapp.core.data.local.database.CinemaxDatabase;
import com.example.cinemaxapp.core.data.local.database.dao.MovieDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideMovieDaoFactory implements Factory<MovieDao> {
  private final Provider<CinemaxDatabase> databaseProvider;

  public DatabaseModule_ProvideMovieDaoFactory(Provider<CinemaxDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MovieDao get() {
    return provideMovieDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMovieDaoFactory create(
      Provider<CinemaxDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMovieDaoFactory(databaseProvider);
  }

  public static MovieDao provideMovieDao(CinemaxDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMovieDao(database));
  }
}
