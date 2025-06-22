plugins {
    alias(libs.plugins.horseracing.android.library)
    alias(libs.plugins.horseracing.android.library.jacoco)
    alias(libs.plugins.horseracing.hilt)
}

android {
    namespace = "com.samples.horseracing.core.datastore"
}

dependencies {
    api(libs.androidx.dataStore)
    //api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(libs.androidx.dataStore.preferences)

    testImplementation(libs.kotlinx.coroutines.test)
}
