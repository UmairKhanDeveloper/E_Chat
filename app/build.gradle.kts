plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.echat"

    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.echat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    // ✅ Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // ✅ Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ✅ Coil (Images)
    implementation("io.coil-kt:coil-compose:2.6.0")

    // ✅ Firebase BOM (IMPORTANT)
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))

    // ✅ Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")

    // ✅ Firestore (chat app ke liye zaroori)
    implementation("com.google.firebase:firebase-firestore-ktx")

    // ✅ Realtime Database (optional)
    implementation("com.google.firebase:firebase-database-ktx")

    // ✅ Google Sign-In
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation("androidx.compose.material:material-icons-extended")
    implementation(libs.firebase.auth)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    // Firebase Authentication (Google login ke liye required)
    implementation("com.google.firebase:firebase-auth-ktx")

    // Optional (agar use kar rahe ho)
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage:20.3.0")


    // Google Authentication (Modern Credential Manager)
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    implementation("io.coil-kt:coil-compose:2.4.0")

    // ✅ Hilt Navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

}