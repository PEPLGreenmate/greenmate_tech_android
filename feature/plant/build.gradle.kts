plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.plant"
}

dependencies {
    implementation(projects.core.util)

    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}
