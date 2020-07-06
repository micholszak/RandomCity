plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(deps.configuration.targetSdkVersion)
    defaultConfig {
        minSdkVersion(deps.configuration.minSdkVersion)
        targetSdkVersion(deps.configuration.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        coreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets.getByName("main") {
        java.srcDir("src/main/kotlin")
    }
    sourceSets.getByName("test") {
        java.srcDir("src/test/kotlin")
    }
    sourceSets.getByName("androidTest") {
        java.srcDir("src/androidTest/kotlin")
    }
}

dependencies {
    implementation(deps.kotlin)
    implementation(deps.adapterDelegates)
    deps.rxJava.forEach(::api)
    deps.appCompat.forEach(::implementation)
    deps.lifecycleEssentials.forEach(::implementation)
    kapt(deps.lifecycleCompiler)
    deps.room.forEach(::api)
    kapt(deps.roomCompiler)
    implementation(deps.googleMaps)

    implementation(deps.dagger)
    deps.daggerAndroid.forEach(::implementation)
    kapt(deps.daggerCompiler)
    kapt(deps.daggerAndroidProcessor)

    deps.testEssentials.forEach(::testImplementation)
    testRuntimeOnly(deps.jupiterEngine)

    coreLibraryDesugaring(deps.desugarJdk)
}