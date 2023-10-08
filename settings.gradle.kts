pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GreenMate"
include(
    ":app",

    ":core:designsystem",
    ":core:data",
    ":core:domain",
    ":core:navigation",
    ":core:model",
    ":core:ui",
    ":core:testing",
    ":core:datastore",

    ":feature:home",
    ":feature:friend",
    ":feature:chat",
    ":feature:diary",
    ":feature:dictionary",
    ":feature:manage",

)
include(":core:domain")
