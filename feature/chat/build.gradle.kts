plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.chat"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}
