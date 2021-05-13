import dk.nillerr.kobray.samples.kobrayshop.*

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation(AndroidX.Compose.ui)
    implementation(AndroidX.Compose.uiTooling)
    implementation(AndroidX.Compose.foundation)
    implementation(AndroidX.Compose.material)
    implementation(AndroidX.Compose.materialIcons)
    implementation(AndroidX.Compose.materialIconsExtended)

    implementation(AndroidX.Activity.compose)

    implementation(AndroidX.Lifecycle.viewModelKtx)
    implementation(AndroidX.Lifecycle.viewModelCompose)
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        applicationId = "dk.nillerr.kobray.samples.kobrayshop"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Kotlin.jvmTarget
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidX.Compose.version
    }
}