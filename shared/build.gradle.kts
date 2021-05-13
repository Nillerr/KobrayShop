import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import dk.nillerr.kobray.samples.kobrayshop.*

plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    id("com.android.library")
}

kotlin {
    // Android
    android()

    // iOS
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        // Common
        val commonMain by getting {
            dependencies {
                // KotlinX
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${KotlinX.nativeVersion}")

                // Kobray
                api(Kobray.core)
                api(Kobray.viewModel)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        // Android
        val androidMain by getting {
            // Annotation Processing
            dependencies {
                api(AndroidX.Lifecycle.viewModelKtx)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        // iOS
        val iosMain by getting
        val iosTest by getting
    }
}

// Annotation Processing
kapt {
    dependencies {
        configurations["kapt"].dependencies.add(compileOnly(Kobray.processor))
    }
    arguments {
        // Kobray
        arg("kapt.kobray.generated", "${buildDir.absolutePath}/generated/kobray")
        arg("kapt.kobray.module", "shared")
    }
}

// Android
android {
    compileSdk = Android.targetSdk

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
    }
}

// iOS
val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)

// Kobray
tasks {
    // Circumvents an issue where `kapt` won't run on subsequent builds, even when the common
    // source has changes, by forcing `kapt` to always run.
    whenTaskAdded {
        if (name.startsWith("kapt")) {
            outputs.upToDateWhen { false }
        }
    }

    // Recommended: Runs `kapt` when building for iOS
    // Change the dependency to a JVM kapt task if you prefer / are not targeting Android
    named("compileKotlinIosArm64") {
        dependsOn("kaptDebugKotlinAndroid")
    }

    named("compileKotlinIosX64") {
        dependsOn("kaptDebugKotlinAndroid")
    }

    // Optional: You can use this task to clean the generated build output as part of your local
    // iOS development workflow.
    register<Delete>("cleanKaptKobray") {
        // Change this value when changing the `kapt.kobray.generated` directory
        delete("${buildDir.absolutePath}/generated/kobray")
    }
}