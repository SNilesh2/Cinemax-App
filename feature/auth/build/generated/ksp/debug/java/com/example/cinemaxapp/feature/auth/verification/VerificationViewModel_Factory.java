package com.example.cinemaxapp.feature.auth.verification;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class VerificationViewModel_Factory implements Factory<VerificationViewModel> {
  @Override
  public VerificationViewModel get() {
    return newInstance();
  }

  public static VerificationViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static VerificationViewModel newInstance() {
    return new VerificationViewModel();
  }

  private static final class InstanceHolder {
    private static final VerificationViewModel_Factory INSTANCE = new VerificationViewModel_Factory();
  }
}
