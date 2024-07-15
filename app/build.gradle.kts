import java.util.Properties


// Handle Props
val tmdbProperties = Properties()
file("tmdb.properties").inputStream().use { tmdbProperties.load(it) }
val TMDB_URL = tmdbProperties.getProperty("TMDB_URL")
val TMDB_KEY = tmdbProperties.getProperty("TMDB_KEY")
val TMDB_TOKEN = tmdbProperties.getProperty("TMDB_TOKEN")


plugins {
    id("kotlin-kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.themoviedbclient"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {

        applicationId = "com.example.themoviedbclient"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "TMDB_KEY", TMDB_KEY)
        buildConfigField("String", "TMDB_TOKEN", TMDB_TOKEN)
        buildConfigField("String", "TMDB_URL", TMDB_URL)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.loggingInterceptor)

    // Dagger Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // CoroutineScope
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation ("androidx.fragment:fragment-ktx:1.8.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}