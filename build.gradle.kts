import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(deps.classpath.androidBuildTools)
        classpath(deps.classpath.kotlinGradle)
        classpath(deps.classpath.junit5Plugin)
    }
}

subprojects {
    repositories {
        google()
        jcenter()
    }

    tasks.withType(JavaCompile::class).configureEach {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType(KotlinJvmCompile::class).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

tasks.register(name = "clean", type = Delete::class) {
    delete(rootProject.buildDir)
}