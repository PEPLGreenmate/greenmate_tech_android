pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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

    ":core:model",
    ":core:data",
    ":core:ui",
    ":core:util",
    ":core:datastore",
    ":core:designsystem",

    ":feature:dictionary",
    ":feature:manage",
    ":feature:friend",
    ":feature:chat",
    ":feature:diary"
)
include(":feature:home")
