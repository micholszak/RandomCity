plugins {
    `kotlin-dsl`
}

sourceSets.getByName("main") {
    java.srcDir("src/main/kotlin")
}

repositories {
    jcenter()
}