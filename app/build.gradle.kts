plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.example.loginsampleapp"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.loginsampleapp"
        minSdk =AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", "\"${BuildConfigFields.baseUrl}\"")
    }

    buildTypes {
        getByName("release") {
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
        jvmTarget = AndroidConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":shared"))

    implementation("androidx.core:core-ktx:${Versions.androidCore}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidCore}")
    implementation("androidx.activity:activity-compose:${Versions.composeActivity}")
    implementation("androidx.compose.ui:ui:${Versions.composeUi}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}")
    implementation("androidx.compose.material:material:${Versions.composeMaterial}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiController}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeRuntime}")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.12.00"))
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.0.0-beta05")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //Network
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.gson}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}")

    //Dependency injection
    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin}")

    // Lottie compose
    implementation("com.airbnb.android:lottie-compose:${Versions.composeLottie}")

    testImplementation("junit:junit:${Versions.jUnit}")
    testImplementation(project(mapOf("path" to ":shared")))
    androidTestImplementation("androidx.test.ext:junit:${Versions.jUnitTest}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoCore}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.composeUi}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.composeUi}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.composeUi}")
}
