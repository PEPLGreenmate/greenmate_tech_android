plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.home"
}

dependencies {
    implementation(projects.feature.search)
    implementation(projects.feature.chat)
    implementation(projects.feature.plant)
    implementation(projects.feature.diary)
    implementation(projects.feature.setting)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}
