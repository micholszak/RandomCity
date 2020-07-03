plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(deps.configuration.targetSdkVersion)

    defaultConfig {
        applicationId = "com.netguru.randomcity"
        minSdkVersion(deps.configuration.minSdkVersion)
        targetSdkVersion(deps.configuration.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            versionNameSuffix = "-Snapshot"
            applicationIdSuffix = ".debug"
            matchingFallbacks = listOf("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(deps.kotlin)
    implementation(project(":randomcity"))
    deps.appCompat.forEach(::implementation)

    implementation(deps.dagger)
    deps.daggerAndroid.forEach(::implementation)
    kapt(deps.daggerCompiler)
    kapt(deps.daggerAndroidProcessor)
}