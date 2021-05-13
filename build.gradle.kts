buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.android.tools.build:gradle:7.0.0-alpha15")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()

        // Kobray
        maven(url = "https://maven.pkg.github.com/nillerr/Kobray") {
            credentials {
                username = project.findProperty("gpr.user") as String?
                password = project.findProperty("gpr.key") as String?
            }

            content {
                includeGroup("dk.nillerr.kobray")
            }
        }
    }
}