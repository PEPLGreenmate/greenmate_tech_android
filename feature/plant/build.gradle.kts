plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.plant"
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}
