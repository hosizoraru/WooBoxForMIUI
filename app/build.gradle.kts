import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.api.variant.BuildConfigField

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace "com.lt2333.simplicitytools"
    compileSdk = 33
    buildToolsVersion = "33.0.1"
    defaultConfig {
        applicationId = "com.lt2333.simplicitytools"
        minSdk = 31
        targetSdk = 33
        versionCode = 69
        versionName = "1.6.9-Voyager"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(listOf("proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.majorVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/**"
            excludes += "/kotlin/**"
            excludes += "/*.txt"
            excludes += "/*.bin"
        }
        dex {
            useLegacyPackaging = true
        }
    }
    applicationVariants.all {
        outputs.all {
            (this as BaseVariantOutputImpl).outputFileName = "WooBoxForMIUI-$versionName-$name.apk"
        }
    }
}

androidComponents {
    onVariants {
       it.buildConfigFields.put(
            "BUILD_TIME", BuildConfigField(
                "String", "\"" + System.currentTimeMillis().toString() + "\"", "build timestamp"
            )
        )
    }
}

dependencies {
    //API
    compileOnly("de.robv.android.xposed:api:82")
    implementation("com.github.kyuubiran:EzXHelper:1.0.3")
    //UI
    implementation(project(":blockmiui"))
    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha05")
    //APP Center
    val appCenterSdkVersion = "5.0.0"
    implementation("com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}")
    implementation("com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}")
    implementation("androidx.core:core-splashscreen:1.0.0")
}