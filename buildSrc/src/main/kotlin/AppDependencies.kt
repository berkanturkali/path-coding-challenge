import Dependencies.DB.Version.room_version
import Dependencies.Network.Version.gson_version
import Dependencies.View.Version.glideVersion

interface Libraries {
    val components: List<String>
}

object Dependencies {

    object AndroidX : Libraries {

        object Version {
            const val coreKtx_version = "1.7.0"
            const val appCompat_version = "1.4.1"
            const val material_version = "1.5.0"
            const val constraint_layout_version = "2.1.3"
        }

        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx_version}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat_version}"
        const val material = "com.google.android.material:material:${Version.material_version}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraint_layout_version}"

        override val components: List<String>
            get() = listOf(
                coreKtx, appCompat, material, constraintLayout
            )
    }

    object Navigation : Libraries {
        object Version {
            const val navigation: String = "2.4.1"
        }

        const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        override val components: List<String>
            get() = listOf(
                navigationFragmentKtx, navigationUiKtx
            )
    }

    object View : Libraries {
        object Version {
            const val swipeRefreshLayout: String = "1.1.0"
            const val glideVersion: String = "4.12.0"
        }

        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"

        const val glide: String = "com.github.bumptech.glide:glide:$glideVersion"

        const val glideAnnotation = "com.github.bumptech.glide:compiler:$glideVersion"

        const val swipeRefreshLayout: String =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"
        override val components: List<String>
            get() = listOf(recyclerView)


    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.9.0"
            const val retrofit: String = "2.9.0"
            const val gson_version = "2.8.7"
            const val paging = "3.0.1"
        }

        const val paging = "androidx.paging:paging-runtime:${Version.paging}"
        const val pagingCommon = "androidx.paging:paging-common-ktx:${Version.paging}"
        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gson_converter: String =
            "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val gson = "com.google.code.gson:gson:$gson_version"

        override val components: List<String> = listOf(
            okhttp, loggingInterceptor, retrofit, gson_converter, gson
        )
    }

    object DI {
        object Version {
            const val javaxInject: String = "1"
            const val daggerHilt: String = "2.38.1"
            const val hiltFragment: String = "1.0.0"
        }

        object AnnotationProcessor {
            const val daggerHilt: String =
                "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Version.javaxInject}"
        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltFragment: String =
            "androidx.hilt:hilt-navigation-fragment:${Version.hiltFragment}"
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.4.2"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val kotlinAndroid: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, kotlinAndroid)
    }

    object DB : Libraries {
        object Version {
            const val room_version = "2.3.0"
        }

        const val roomRuntime = "androidx.room:room-runtime:$room_version"
        const val roomKtx = "androidx.room:room-ktx:$room_version"
        const val roomCompiler = "androidx.room:room-compiler:$room_version"
        const val roomCommon = "androidx.room:room-common:$room_version"

        override val components: List<String>
            get() = listOf(
                roomKtx, roomRuntime
            )
    }

    object Logging {
        object Version {
            const val timber = "4.7.1"
        }

        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Test {
        object Version {
            const val junit_version = "4.13.2"
            const val junit_ext_version = "1.1.3"
            const val espresso_version = "3.4.0"
        }

        const val junit = "junit:junit:${Version.junit_version}"
        const val junitExt = "androidx.test.ext:junit:${Version.junit_ext_version}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso_version}"
    }
}