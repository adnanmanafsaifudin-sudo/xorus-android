plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "id.xorus.agent"
    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    defaultConfig {
        applicationId = "id.xorus.agent"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    val cameraVersion = "1.4.2"

    implementation("androidx.camera:camera-core:$cameraVersion")
    implementation("androidx.camera:camera-camera2:$cameraVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraVersion")
    implementation("androidx.camera:camera-view:$cameraVersion")
}
