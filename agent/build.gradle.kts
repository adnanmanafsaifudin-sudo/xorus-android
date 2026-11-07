plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "id.xorus.agent"
    compileSdk = 35

    defaultConfig {
        applicationId = "id.xorus.agent"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
