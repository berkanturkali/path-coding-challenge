plugins {
    androidLib
    daggerHilt
}
android.defaultConfig.buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/\"")

dependencies {

    //network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.moshi)

    //dagger hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)


    /* test */
    testImplementation(Dependencies.Test.mockWebServer)
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Network.retrofitMoshi)
    testImplementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Test.coroutines)
}