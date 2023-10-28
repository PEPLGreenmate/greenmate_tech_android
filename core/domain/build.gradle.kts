plugins {
    id("pepl.android.library")
}

android {
    namespace = "com.pepl.greenmate.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.inject)
}
