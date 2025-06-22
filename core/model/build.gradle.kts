
plugins {
    alias(libs.plugins.horseracing.android.library)
    alias(libs.plugins.horseracing.android.library.jacoco)
    alias(libs.plugins.horseracing.hilt)
}

android {
    namespace = "com.samples.horseracing.core.model"
}

dependencies {
    api(libs.androidx.dataStore)

    implementation(libs.androidx.dataStore.preferences)

    testImplementation(libs.kotlinx.coroutines.test)
}
