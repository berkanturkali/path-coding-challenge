import ProjectLib.remote

plugins {
    androidLib
    daggerHilt
    parcelize
}
android.defaultConfig.buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/\"")

dependencies {

    //project lib
    implementation(project(remote))

    //network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.moshi)

    //dagger hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

    //paging 3
    implementation(Dependencies.Paging.paging)

    //swipe-refresh
    implementation(Dependencies.View.swipeRefreshLayout)


    /* test */
    testImplementation(Dependencies.Test.mockWebServer)
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Network.retrofitMoshi)
    testImplementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.mockk)
}