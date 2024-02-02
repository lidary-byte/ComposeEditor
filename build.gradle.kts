@file:Suppress("UnstableApiUsage")


import org.jetbrains.compose.desktop.application.dsl.TargetFormat
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}


group = "com.lidary"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://packages.jetbrains.team/maven/p/kpm/public/")
    google()
}

java {
    toolchain {
        vendor = JvmVendorSpec.JETBRAINS
        languageVersion = JavaLanguageVersion.of(17)
    }
}
kotlin {
    jvm {
        jvmToolchain {
            vendor = JvmVendorSpec.JETBRAINS
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs) {
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
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        buildTypes.release.proguard {
            configurationFiles.from(project.file("compose-desktop.pro"))
        }
        nativeDistributions {
            modules("jdk.unsupported")

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe, TargetFormat.Pkg)

            packageName = "ComposeEditor"
            packageVersion = "1.0.0"
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


