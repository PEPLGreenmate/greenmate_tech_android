import com.pepl.greenmate.configureHiltAndroid
import com.pepl.greenmate.configureKotestAndroid
import com.pepl.greenmate.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
