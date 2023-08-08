plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.baubap.domain"
    compileSdk =  AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":shared"))

    //Dependency injection
    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin}")

    implementation("androidx.core:core-ktx:${Versions.androidCore}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.androidMaterial}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("org.mockito:mockito-inline:${Versions.mockitoInline}")
    testImplementation("junit:junit:${Versions.jUnit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.jUnitTest}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoCore}")
}