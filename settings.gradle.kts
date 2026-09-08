pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cinemax App"
include(":app")
// Feature modules
include(":feature:auth")
include(":feature:home")
include("feature:movie_details")

// Core modules
include(":core:designsystem")
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":core:model")
include(":core:common")

include(":feature:movie_details")
