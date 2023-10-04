import com.pepl.greenmate.configureCoroutineAndroid
import com.pepl.greenmate.configureHiltAndroid
import com.pepl.greenmate.configureKotest
import com.pepl.greenmate.configureKotlinAndroid

plugins {
    id("com.android.library")
    id("droidknights.verify.detekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
