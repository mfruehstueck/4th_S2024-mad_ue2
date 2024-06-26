plugins {
    // before exercise 5
    //id("com.android.application")
    //id("org.jetbrains.kotlin.android")

    // exercise 5
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.example.movieappmad24"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieappmad24"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        // before exercise 5
        //kotlinCompilerExtensionVersion = "1.5.1"

        // exercise 5
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:1.10.1")
    //noinspection GradleDependency
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    //noinspection GradleDependency
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("io.coil-kt:coil-compose:2.6.0")

    // navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // modelView
    val mv_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$mv_version")


    val media3_version = "1.2.1"

    // exoPlayer
    //noinspection GradleDependency
    implementation("androidx.media3:media3-exoplayer:$media3_version")
    //noinspection GradleDependency
    implementation("androidx.media3:media3-exoplayer-dash:$media3_version")
    //noinspection GradleDependency
    implementation("androidx.media3:media3-ui:$media3_version")

    // room
    val room_version = "2.6.1"

    // room dependencies
    implementation("androidx.room:room-runtime:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
}