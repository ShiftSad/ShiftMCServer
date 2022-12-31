import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`

    kotlin("jvm") version "1.7.21"
    id("net.kyori.blossom") version "1.2.0" // Text replacement
    id("com.github.johnrengelman.shadow") version "7.1.2" // Shadowing
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Minestom
    implementation("com.github.Minestom:Minestom:91a344aa92")

    // Tiny Log
    implementation("org.tinylog:tinylog-api-kotlin:2.5.0")
    implementation("org.tinylog:tinylog-impl:2.5.0")

    testImplementation(kotlin("test"))
}

blossom {
    val gitCommit = getOutputOf("git rev-parse --short HEAD").trim()
    val gitBranch = getOutputOf("git rev-parse --abbrev-ref HEAD").trim()
    val gitCommitDate = getOutputOf("git show -s --format=%ci").trim()

    replaceToken("%%GIT_COMMIT%%", gitCommit)
    replaceToken("%%GIT_BRANCH%%", gitBranch)
    replaceToken("%%GIT_COMMIT_DATE%%", gitCommitDate)
}

fun getOutputOf(command: String): String {
    val process = Runtime.getRuntime().exec(command)
    process.waitFor()
    return process.inputStream.bufferedReader().readText()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.properties["group"] as? String?
            artifactId = project.name
            version = project.properties["version"] as? String?

            from(components["java"])
        }
    }
}