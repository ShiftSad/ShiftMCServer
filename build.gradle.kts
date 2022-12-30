import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Minestom (Main dependency)
    implementation("com.github.Minestom:Minestom:91a344aa92")

    // Tiny Log
    implementation("org.tinylog:tinylog-api-kotlin:2.5.0")
    implementation("org.tinylog:tinylog-impl:2.5.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}