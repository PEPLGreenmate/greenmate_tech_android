plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.friend"
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}
