import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply(plugin = "com.android.application")
apply(plugin = "org.jetbrains.kotlin.android")

val debugKeystorePassword = providers.gradleProperty("partnerlogDebugKeystorePassword").orElse("android")
val debugKeyPassword = providers.gradleProperty("partnerlogDebugKeyPassword").orElse("android")

configure<ApplicationExtension> {
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
            storeFile = if (signingDirKeystore.exists()) {
                signingDirKeystore
            } else {
                throw GradleException(
                    "Missing signing/debug.keystore. Restore it from CI secrets or create a local debug keystore at /home/runner/work/partnerlog/partnerlog/signing/debug.keystore."
                )
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
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    add("implementation", "androidx.core:core-ktx:1.13.1")
    add("implementation", "androidx.appcompat:appcompat:1.7.0")
    add("implementation", "com.google.android.material:material:1.12.0")
    add("implementation", "androidx.constraintlayout:constraintlayout:2.1.4")
}
