object deps {
    internal object versions {
        const val kotlin = "1.3.72"
        const val dagger = "2.28.1"
        const val jUnit5 = "5.6.2"
        const val room = "2.2.5"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
    const val desugarJdk = "com.android.tools:desugar_jdk_libs:1.0.5"
    val rxJava = listOf(
        "io.reactivex.rxjava2:rxjava:2.2.19",
        "io.reactivex.rxjava2:rxandroid:2.1.1"
    )

    val room = listOf(
        "androidx.room:room-runtime:${versions.room}",
        "androidx.room:room-rxjava2:${versions.room}"
    )
    const val roomCompiler = "androidx.room:room-compiler:${versions.room}"

    val appCompat = arrayOf(
        "androidx.appcompat:appcompat:1.1.0",
        "androidx.constraintlayout:constraintlayout:1.1.3"
    )

    const val dagger = "com.google.dagger:dagger:${versions.dagger}"
    val daggerAndroid = listOf(
        "com.google.dagger:dagger-android:${versions.dagger}",
        "com.google.dagger:dagger-android-support:${versions.dagger}"
    )
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${versions.dagger}"
    const val threeTen = "com.jakewharton.threetenabp:threetenabp:1.2.4"

    val testEssentials = listOf(
        "org.junit.jupiter:junit-jupiter-api:${versions.jUnit5}",
        "org.junit.jupiter:junit-jupiter-params:${versions.jUnit5}",
        "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0",
        "org.assertj:assertj-core:3.15.0"
    )
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${versions.jUnit5}"

    val espresso = listOf(
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