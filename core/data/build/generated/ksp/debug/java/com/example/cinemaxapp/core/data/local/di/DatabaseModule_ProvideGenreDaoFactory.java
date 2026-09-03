package com.example.cinemaxapp.core.data.local.di;

import com.example.cinemaxapp.core.data.local.database.CinemaxDatabase;
import com.example.cinemaxapp.core.data.local.database.dao.GenreDao;
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
public final class DatabaseModule_ProvideGenreDaoFactory implements Factory<GenreDao> {
  private final Provider<CinemaxDatabase> databaseProvider;

  public DatabaseModule_ProvideGenreDaoFactory(Provider<CinemaxDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public GenreDao get() {
    return provideGenreDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideGenreDaoFactory create(
      Provider<CinemaxDatabase> databaseProvider) {
    return new DatabaseModule_ProvideGenreDaoFactory(databaseProvider);
  }

  public static GenreDao provideGenreDao(CinemaxDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideGenreDao(database));
  }
}
