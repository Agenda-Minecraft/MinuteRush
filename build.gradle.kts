import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

val baseName = "MinuteRush"
group = "cat.kiwi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url="https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("org.json:json:20220320")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(archiveBaseName.get())
        relocate("org.json","cat.kiwi.org.json")
        relocate("org.gson","cat.kiwi.org.gson")
        dependencies {
            exclude(dependency("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT"))
        }
    }
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val fileName = "$baseName-$version-all.jar"
tasks.register<Copy>("apply"){
    dependsOn("shadowJar")
    from("build/libs/$fileName")
    into("C:\\Users\\moeKiwiSAMA\\Desktop\\server\\plugins\\")
}