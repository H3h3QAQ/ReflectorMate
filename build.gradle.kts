plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij") version "1.17.4"
//    id("org.jetbrains.intellij.platform") version "2.1.0"
}

group = "com.yulate"
version = "1.0.4-SNAPSHOT"

repositories {
//    mavenCentral()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
//    version.set("2023.2.6")
    version.set("2024.2")
    type.set("IC") // Target IDE Platform
//    localPath.set("/Applications/IntelliJ IDEA.app/Contents")
    plugins.set(listOf("com.intellij.java", "java", "org.jetbrains.idea.maven"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
