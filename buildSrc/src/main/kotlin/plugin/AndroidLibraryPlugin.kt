package plugin

import Dependencies
import androidModule
import androidTestImplementation
import extensions.AndroidLib
import extensions.ProjectExtension
import implementAll
import implementation
import kotlinAndroid
import kotlinKapt
import testImplementation

class AndroidLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            androidModule
            kotlinAndroid
            kotlinKapt
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementAll(Dependencies.AndroidX.components)
            implementation(Dependencies.Logging.timber)
            testImplementation(Dependencies.Test.junit)
            androidTestImplementation(
                Dependencies.Test.junitExt,
                Dependencies.Test.espresso
            )
        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidLib)
}