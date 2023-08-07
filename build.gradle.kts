buildscript {

    buildscript {
        repositories {
            google()
            mavenCentral()
        }
    }
}
plugins {
    id("com.android.application") version Plugins.androidApplicationPlugin apply false
    id("com.android.library") version Plugins.androidLibraryPlugin apply false
    id("org.jetbrains.kotlin.android") version Plugins.kotlinGradlePlugin apply false
    id("io.gitlab.arturbosch.detekt") version Plugins.detektPlugin  apply false
}