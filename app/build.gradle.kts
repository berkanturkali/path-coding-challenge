import ProjectLib.characters
import ProjectLib.common
import ProjectLib.core
import ProjectLib.remote

plugins {
    androidApp
    daggerHilt
}
kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

android.buildFeatures.viewBinding = true
dependencies {

    //project libs
    implementation(project(remote))
    implementation(project(core))
    implementation(project(characters))
    implementation(project(common))

    //navigation
    implementation(Dependencies.Navigation.navigationUiKtx)
    implementation(Dependencies.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Navigation.fragmentKtx)

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    implementation("com.google.firebase:firebase-analytics-ktx:20.1.2")
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

    //timber
    implementation(Dependencies.Logging.timber)


}