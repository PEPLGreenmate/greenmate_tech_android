plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.search"
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}