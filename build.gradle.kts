import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
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

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
//    {
//        exclude("org.jetbrains.compose.material")
//    }
    implementation("moe.tlaster:precompose:1.5.10")
    implementation("moe.tlaster:precompose-viewmodel:1.5.10")
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.0")
//    implementation("com.bybutter.compose:compose-jetbrains-expui-theme:2.0.0")
    implementation("org.jetbrains.jewel:jewel-int-ui-standalone:0.13.2")
    implementation("org.jetbrains.jewel:jewel-int-ui-decorated-window:0.13.2")

//    implementation("com.halilibo.compose-richtext:richtext-ui:0.20.0")
//    implementation("com.halilibo.compose-richtext:richtext-commonmark:0.20.0")
    implementation("io.noties.markwon:core:4.6.2")
//    implementation("io.noties.markwon:editor:4.6.2")
//    implementation("io.noties.markwon:ext-latex:4.6.2")
//    implementation("io.noties.markwon:ext-strikethrough:4.6.2")
//    implementation("io.noties.markwon:ext-tables:4.6.2")
//    implementation("io.noties.markwon:ext-tasklist:4.6.2")
//    implementation("io.noties.markwon:html:4.6.2")
//    implementation("io.noties.markwon:image:4.6.2")
////    implementation("io.noties.markwon:image-coil:4.6.2")
//    implementation("io.noties.markwon:inline-parser:4.6.2")
//    implementation("io.noties.markwon:linkify:4.6.2")
//    implementation("io.noties.markwon:simple-ext:4.6.2")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "editor"
            packageVersion = "1.0.0"
        }
    }
}
