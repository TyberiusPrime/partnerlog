plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val debugKeystorePassword = providers.gradleProperty("partnerlogDebugKeystorePassword").orElse("android")
val debugKeyPassword = providers.gradleProperty("partnerlogDebugKeyPassword").orElse("android")

android {
    namespace = "com.tyberiusprime.partnerlog"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tyberiusprime.partnerlog"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0"
    }

    signingConfigs {
        create("partnerlogDebug") {
            val signingDirKeystore = rootProject.file("signing/debug.keystore")
            val rootKeystore = rootProject.file("debug.keystore")

            storeFile = when {
                signingDirKeystore.exists() -> signingDirKeystore
                rootKeystore.exists() -> rootKeystore
                else -> signingDirKeystore
            }
            storePassword = debugKeystorePassword.get()
            keyAlias = "partnerlogdebug"
            keyPassword = debugKeyPassword.get()
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("partnerlogDebug")
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
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
