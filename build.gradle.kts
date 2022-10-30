import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

val baseName = "MinuteRush"
group = "cat.kiwi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url="https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url="https://oss.sonatype.org/content/repositories/snapshots")
    maven(url="https://repo.extendedclip.com/content/repositories/placeholderapi")
    maven(url="https://repo.codemc.org/repository/maven-public/")
    maven(url="https://jitpack.io")

}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("org.json:json:20220320")
    implementation("com.github.Agenda-Minecraft:MEtcd:2.0.4")
    testImplementation("org.json:json:20220320")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(archiveBaseName.get())
        relocate("org.json", "cat.kiwi.org.json")
        dependencies {
            exclude(dependency("com.github.Agenda-Minecraft:MEtcd:dd5c9694e0"))
            exclude(dependency("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT"))
        }
    }
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val fileName = "$baseName-$version-all.jar"
tasks.register<Copy>("apply") {
    dependsOn("shadowJar")
    from("build/libs/$fileName")
    into("C:\\Users\\moeKiwiSAMA\\Desktop\\server\\plugins\\")
}