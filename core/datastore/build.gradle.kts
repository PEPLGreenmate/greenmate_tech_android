plugins {
    id("pepl.android.library")
}

android {
    namespace = "com.pepl.greenmate.core.datastore"
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
