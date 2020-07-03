import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

object deps {
    internal object versions {
        const val kotlin = "1.3.72"
        const val dagger = "2.28.1"
        const val jUnit5 = "5.6.2"
        const val room = "2.2.5"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
    val rxJava = arrayOf(
        "io.reactivex.rxjava2:rxjava:2.2.19",
        "io.reactivex.rxjava2:rxandroid:2.1.1"
    )

    const val dagger = "com.google.dagger:dagger:${versions.dagger}"
    val daggerAndroid = arrayOf(
        "com.google.dagger:dagger-android:${versions.dagger}",
        "com.google.dagger:dagger-android-support:${versions.dagger}"
    )
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${versions.dagger}"

    val room = arrayOf(
        "androidx.room:room-runtime:${versions.room}",
        "androidx.room:room-rxjava2:${versions.room}"
    )

    val appCompat = arrayOf(
        "androidx.appcompat:appcompat:1.1.0",
        "androidx.constraintlayout:constraintlayout:1.1.3"
    )

    val jupiter = arrayOf(
        "org.junit.jupiter:junit-jupiter-api:${versions.jUnit5}",
        "org.junit.jupiter:junit-jupiter-params:${versions.jUnit5}"
    )
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${versions.jUnit5}"

    val espresso = arrayOf(
        "androidx.test.espresso:espresso-core:3.2.0",
        "androidx.test:runner:1.2.0",
        "androidx.test:rules:1.2.0"
    )

    object classpath {
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        const val androidBuildTools = "com.android.tools.build:gradle:4.0.0"
        const val junit5Plugin = "de.mannodermaus.gradle.plugins:android-junit5:1.6.2.0"
    }

    object configuration {
        const val minSdkVersion = 21
        const val targetSdkVersion = 29
    }
}