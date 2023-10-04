import com.pepl.greenmate.configureKotest
import com.pepl.greenmate.configureKotlin

plugins {
    kotlin("jvm")
    id("droidknights.verify.detekt")
}

configureKotlin()
configureKotest()
