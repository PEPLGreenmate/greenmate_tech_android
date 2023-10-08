plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.diary"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.shimmer)
    implementation(libs.kotlinx.immutable)
}
