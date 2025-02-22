import java.util.Properties


// Handle Props
val tmdbProperties = Properties()
file("tmdb.properties").inputStream().use { tmdbProperties.load(it) }
val TMDB_URL = tmdbProperties.getProperty("TMDB_URL")
val TMDB_IMAGES_URL = tmdbProperties.getProperty("TMDB_IMAGES_URL")
val TMDB_KEY = tmdbProperties.getProperty("TMDB_KEY")
val TMDB_TOKEN = tmdbProperties.getProperty("TMDB_TOKEN")


plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
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
        buildConfigField("String", "TMDB_IMAGES_URL", TMDB_IMAGES_URL)
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
    implementation(libs.androidx.viewpager2)

    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.loggingInterceptor)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // Dagger Hilt
    implementation(libs.hilt)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.hilt.compiler)

    // CoroutineScope
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.fragment.ktx)

    // Glide
    implementation(libs.glide)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}