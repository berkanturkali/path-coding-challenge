plugins {
    androidLib
}

android.testOptions.unitTests {
    isIncludeAndroidResources = true
}

dependencies {
    implementation(Dependencies.View.glide)
    kapt(Dependencies.View.glideAnnotation)
    implementation(Dependencies.View.swipeRefreshLayout)

    testImplementation("androidx.test:core:1.0.0")

    testImplementation(Dependencies.Test.roboElectric)
}