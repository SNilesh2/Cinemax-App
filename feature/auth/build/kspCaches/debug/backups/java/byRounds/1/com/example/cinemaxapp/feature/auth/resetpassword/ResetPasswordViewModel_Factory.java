package com.example.cinemaxapp.feature.auth.resetpassword;

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
public final class ResetPasswordViewModel_Factory implements Factory<ResetPasswordViewModel> {
  @Override
  public ResetPasswordViewModel get() {
    return newInstance();
  }

  public static ResetPasswordViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ResetPasswordViewModel newInstance() {
    return new ResetPasswordViewModel();
  }

  private static final class InstanceHolder {
    private static final ResetPasswordViewModel_Factory INSTANCE = new ResetPasswordViewModel_Factory();
  }
}
