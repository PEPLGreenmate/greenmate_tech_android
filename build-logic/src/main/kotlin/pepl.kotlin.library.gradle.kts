import com.pepl.greenmate.configureKotest
import com.pepl.greenmate.configureKotlin

plugins {
    kotlin("jvm")
    id("pepl.verify.detekt")
}

configureKotlin()
configureKotest()
