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
include(":data:notes:api")
include(":core:presentation")
include(":core:note-presentation")
include(":core:presentation-domain-mapper")
include(":feature:notes-home")
include(":core:logging")
include(":data:room")
include(":utils:result")
include(":feature:create-note")
include(":core:permission")
include(":core:gallery")
