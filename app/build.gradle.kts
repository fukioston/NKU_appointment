plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp").version("1.9.20-1.0.14")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.alumni_campus_visit_appointment_system"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.alumni_campus_visit_appointment_system"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.example.alumni_campus_visit_appointment_system"
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    // Kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    // Retrofit with Kotlin serialization Converter

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")


    // Testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.3-alpha")
    implementation("com.google.accompanist:accompanist-pager:0.24.3-alpha")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.24.3-alpha")

    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
//    implementation("androidx.room:room-paging:$roomVersion")
//    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

}