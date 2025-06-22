
plugins {
    alias(libs.plugins.horseracing.android.feature)
    alias(libs.plugins.horseracing.android.library.compose)
    alias(libs.plugins.horseracing.android.library.jacoco)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.samples.horseracing.feature.history"
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(projects.core.datastore)
    //implementation(projects.core.domain)
    //implementation(projects.core.notifications)

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    //testImplementation(projects.core.testing)
    //testDemoImplementation(projects.core.screenshotTesting)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    //androidTestImplementation(projects.core.testing)
}
