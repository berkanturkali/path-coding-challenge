import ProjectLib.core
import ProjectLib.remote

plugins {
    androidApp
}
dependencies {

    //project libs
    implementation(project(remote))
    implementation(project(core))
}