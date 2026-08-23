package com.example.cinemaxapp.core.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  @Override
  public MovieRepositoryImpl get() {
    return newInstance();
  }

  public static MovieRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MovieRepositoryImpl newInstance() {
    return new MovieRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final MovieRepositoryImpl_Factory INSTANCE = new MovieRepositoryImpl_Factory();
  }
}
