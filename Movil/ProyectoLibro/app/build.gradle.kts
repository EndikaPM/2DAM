plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.proyectolibro"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.proyectolibro"
        minSdk = 24
        targetSdk = 36
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Para escanear códigos de barras (ZXing)
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    // Para hacer peticiones HTTP (obtener datos de la API)
    implementation ("com.android.volley:volley:1.2.1")
    // Para cargar imágenes desde URLs (las portadas)
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
}