rootProject.name = "NotesCMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":core:copy-image")
include(":core:gallery")
include(":core:logging")
include(":core:note-presentation")
include(":core:permission")
include(":core:presentation")
include(":core:presentation-domain-mapper")
include(":data:notes:api")
include(":data:room")
include(":feature:create-note")
include(":feature:notes-home")
include(":utils:result")
