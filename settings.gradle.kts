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
    ":core:util",

    ":feature:home",
    ":feature:plant",
    ":feature:search",
    ":feature:chat",
    ":feature:diary",
    ":feature:setting",

    )
include(":search")
