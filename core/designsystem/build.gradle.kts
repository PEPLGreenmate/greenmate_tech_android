plugins {
    id("pepl.android.library")
    id("pepl.android.compose")
}

android {
    namespace = "com.pepl.greenmate.core.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)

    implementation(libs.landscapist.bom)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.placeholder)

    implementation(libs.androidx.glance)

}
