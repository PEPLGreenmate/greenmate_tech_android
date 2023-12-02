plugins {
    id("pepl.android.feature")
    //kotlin("plugin.serialization") version "1.8.21"
}

android {
    namespace = "com.pepl.greenmate.feature.chat"
}

dependencies {
    implementation(projects.core.util)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
    implementation(project(mapOf("path" to ":feature:diary")))
    //implementation(libs.hilt.android)
    //kapt(libs.hilt.compiler)
    //implementation(project(mapOf("path" to ":feature:diary")))
}
