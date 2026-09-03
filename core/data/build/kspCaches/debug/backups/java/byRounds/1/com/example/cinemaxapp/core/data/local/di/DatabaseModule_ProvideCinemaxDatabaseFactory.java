package com.example.cinemaxapp.core.data.local.di;

import android.content.Context;
import com.example.cinemaxapp.core.data.local.database.CinemaxDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideCinemaxDatabaseFactory implements Factory<CinemaxDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideCinemaxDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CinemaxDatabase get() {
    return provideCinemaxDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideCinemaxDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideCinemaxDatabaseFactory(contextProvider);
  }

  public static CinemaxDatabase provideCinemaxDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCinemaxDatabase(context));
  }
}
