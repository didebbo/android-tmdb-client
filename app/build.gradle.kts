import java.util.Properties


// Handle Props
val props = Properties()
file("props.properties").inputStream().use { props.load(it) }
val TMDB_KEY = props.getProperty("TMDB_KEY")
val TMDB_TOKEN = props.getProperty("TMDB_TOKEN")


plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.themoviedbclient"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}