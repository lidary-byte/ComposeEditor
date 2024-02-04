@file:Suppress("UnstableApiUsage")


import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("dev.hydraulic.conveyor") version "1.2"
}


group = "com.lidary"
version = "1.0.0"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://packages.jetbrains.team/maven/p/kpm/public/")
    maven { url = uri("https://jitpack.io") }
    google()
}

java {
    toolchain {
        vendor = JvmVendorSpec.JETBRAINS
        languageVersion = JavaLanguageVersion.of(17)
    }
}


dependencies {
    implementation(compose.desktop.linux_x64) {
        exclude(group = "org.jetbrains.compose.material")
    }
    implementation(compose.desktop.macos_x64) {
        exclude(group = "org.jetbrains.compose.material")
    }
    implementation(compose.desktop.macos_arm64) {
        exclude(group = "org.jetbrains.compose.material")
    }
    implementation(compose.desktop.windows_x64) {
        exclude(group = "org.jetbrains.compose.material")
    }
    implementation("org.jetbrains.jewel:jewel-int-ui-standalone:0.13.2")
    implementation("org.jetbrains.jewel:jewel-int-ui-decorated-window:0.13.2")
    // 文件内容高亮 https://github.com/bobbylight/RSyntaxTextArea
    implementation("com.fifesoft:rsyntaxtextarea:3.3.4")
    implementation("com.fifesoft:rstaui:3.3.1")
    implementation("moe.tlaster:precompose:1.5.10")
    implementation("moe.tlaster:precompose-viewmodel:1.5.10")
    // Swing UI相关
//    implementation("com.formdev:flatlaf:3.3")
//                implementation("org.jetbrains.skiko:skiko-awt-runtime-macos-arm64:${extra["skiko.version"] as String}")
//                implementation("org.jetbrains.compose.components:components-splitpane-desktop:${extra["compose.version"] as String}")
//                implementation("com.fifesoft:rsyntaxtextarea:${extra["rsyntaxtextarea.version"] as String}")
//                implementation("com.fifesoft:rstaui:${extra["rstaui.version"] as String}")
//                implementation("net.java.dev.jna:jna:${extra["jna.version"] as String}")
}

compose.desktop {
    application {
        javaHome = "C:\\Users\\lidar\\.jdks\\jbr-17.0.9-1"
        mainClass = "MainKt"
        buildTypes.release.proguard {
            configurationFiles.from(project.file("compose-desktop.pro"))
        }
        nativeDistributions {
            outputBaseDir.set(project.projectDir.resolve("app"))
            modules("java.instrument", "jdk.unsupported")
            targetFormats(TargetFormat.Dmg, TargetFormat.Deb, TargetFormat.Exe)

            packageName = "ComposeEditor"
            description = "基于Compose的Editor"
//            vendor = "Romain Guy"
//            licenseFile = rootProject.file("LICENSE")

//            macOS {
//                dockName = "Kotlin Explorer"
//                bundleID = "dev.romainguy.kotlin.explorer"
//            }
        }
    }
}


