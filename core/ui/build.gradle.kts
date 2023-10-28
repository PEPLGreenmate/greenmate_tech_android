plugins {
    id("pepl.android.library")
    id("pepl.android.compose")
}

android {
    namespace = "com.pepl.greenmate.core.ui"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}
