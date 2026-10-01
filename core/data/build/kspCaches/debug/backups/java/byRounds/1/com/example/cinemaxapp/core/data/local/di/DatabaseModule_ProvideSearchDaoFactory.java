package com.example.cinemaxapp.core.data.local.di;

import com.example.cinemaxapp.core.data.local.database.CinemaxDatabase;
import com.example.cinemaxapp.core.data.local.database.dao.SearchDao;
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
public final class DatabaseModule_ProvideSearchDaoFactory implements Factory<SearchDao> {
  private final Provider<CinemaxDatabase> databaseProvider;

  public DatabaseModule_ProvideSearchDaoFactory(Provider<CinemaxDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SearchDao get() {
    return provideSearchDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideSearchDaoFactory create(
      Provider<CinemaxDatabase> databaseProvider) {
    return new DatabaseModule_ProvideSearchDaoFactory(databaseProvider);
  }

  public static SearchDao provideSearchDao(CinemaxDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSearchDao(database));
  }
}
