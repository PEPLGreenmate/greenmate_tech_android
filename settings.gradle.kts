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
    ":feature:plant",
    ":feature:chat",
    ":feature:diary",
    ":feature:setting",

)
include(":core:domain")
