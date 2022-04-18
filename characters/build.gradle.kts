import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
    daggerHilt
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
}