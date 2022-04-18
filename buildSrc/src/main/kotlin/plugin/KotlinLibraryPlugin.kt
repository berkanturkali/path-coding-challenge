package plugin

import extensions.JavaExtension
import extensions.KotlinExtension
import extensions.ProjectExtension
import kotlinKapt
import implementation
import kapt


class KotlinLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(KOTLIN_PLUGIN_ID)
            kotlinKapt
        }

    override val libraryConfig: LibraryConfig
        get() = {
            implementation(
                Dependencies.DI.hiltCore
            )
            kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
        }

    override val extensions: Array<ProjectExtension>
        get() = arrayOf(
            ProjectExtension.KotlinExtension,
            ProjectExtension.JavaExtension
        )

    private companion object {
        const val KOTLIN_PLUGIN_ID: String = "kotlin"
    }
}