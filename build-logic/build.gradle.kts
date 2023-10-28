plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.verify.detektPlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "pepl.android.hilt"
            implementationClass = "com.pepl.greenmate.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "pepl.kotlin.hilt"
            implementationClass = "com.pepl.greenmate.HiltKotlinPlugin"
        }
    }
}
