import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
    daggerHilt
    safeArgs
}

dependencies {

    //project libs
    implementation(project(core))
    implementation(project(common))

    //dagger hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

    //paging 3
    implementation(Dependencies.Paging.paging)

    //fragment-ktx
    implementation(Dependencies.AndroidX.fragmentKtx)

    //swipe-refresh
    implementation(Dependencies.View.swipeRefreshLayout)

    //navigation-component
    implementation(Dependencies.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Navigation.navigationUiKtx)
}