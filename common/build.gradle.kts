plugins {
    androidLib
}

dependencies {
    implementation(Dependencies.View.glide)
    kapt(Dependencies.View.glideAnnotation)
    implementation(Dependencies.View.swipeRefreshLayout)
}