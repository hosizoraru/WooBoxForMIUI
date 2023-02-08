// Top-level build file where you can add configuration options common to all sub-projects/modules.
android{
    namespace 'com.lt2333.simplicitytools'
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0-alpha02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("Delete", Delete::class) {
    delete(rootProject.buildDir)
}