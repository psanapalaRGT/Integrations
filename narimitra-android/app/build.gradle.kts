plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rgt.narimitra"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rgt.narimitra"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    signingConfigs {
        // Prototype-only keystore, committed so every build (local or CI) carries
        // the same signature and installs as an update over previous builds.
        // Replace with a private keystore before any store distribution.
        create("release") {
            storeFile = rootProject.file("keystore/narimitra.jks")
            storePassword = "narimitra-proto"
            keyAlias = "narimitra"
            keyPassword = "narimitra-proto"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.webkit:webkit:1.12.1")
}
