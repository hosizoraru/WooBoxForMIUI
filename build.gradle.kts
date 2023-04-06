// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val agp_version by extra("7.4.2")
    val agp_version1 by extra(agp_version)
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$agp_version1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("Delete", Delete::class) {
    delete(rootProject.buildDir)
}