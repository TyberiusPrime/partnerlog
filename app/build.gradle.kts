apply(plugin = "com.android.application")
apply(plugin = "org.jetbrains.kotlin.android")

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
            storeFile = file("../signing/debug.keystore")
            storePassword = "android"
            keyAlias = "partnerlogdebug"
            keyPassword = "android"
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
