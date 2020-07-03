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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(deps.kotlin)
    deps.appCompat.forEach(::implementation)
}