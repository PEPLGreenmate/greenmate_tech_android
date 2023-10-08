plugins {
    id("pepl.android.feature")
}

android {
    namespace = "com.pepl.greenmate.feature.home"
}

dependencies {
    implementation(projects.feature.friend)
    implementation(projects.feature.chat)
    implementation(projects.feature.diary)
    implementation(projects.feature.dictionary)
    implementation(projects.feature.manage)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}
