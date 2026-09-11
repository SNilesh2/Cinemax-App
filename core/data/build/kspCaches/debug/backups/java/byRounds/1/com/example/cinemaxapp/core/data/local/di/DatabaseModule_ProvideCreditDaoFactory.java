package com.example.cinemaxapp.core.data.local.di;

import com.example.cinemaxapp.core.data.local.database.CinemaxDatabase;
import com.example.cinemaxapp.core.data.local.database.dao.CreditDao;
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
public final class DatabaseModule_ProvideCreditDaoFactory implements Factory<CreditDao> {
  private final Provider<CinemaxDatabase> databaseProvider;

  public DatabaseModule_ProvideCreditDaoFactory(Provider<CinemaxDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CreditDao get() {
    return provideCreditDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCreditDaoFactory create(
      Provider<CinemaxDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCreditDaoFactory(databaseProvider);
  }

  public static CreditDao provideCreditDao(CinemaxDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCreditDao(database));
  }
}
