import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
}

android {
    // compileSdkPreview = "UpsideDownCake"
    compileSdk = 33
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.lt2333.simplicitytools"
        minSdk = 31
        targetSdk = 33
        versionCode = 76
        versionName = "1.7.5-Voyager"
        buildConfigField("String", "BUILD_TIME", "\"${System.currentTimeMillis()}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(listOf("proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.majorVersion
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

dependencies {
    implementation("androidx.core:core-ktx:1.10.0-beta01")
    //API
    compileOnly("de.robv.android.xposed:api:82")
    implementation("com.github.kyuubiran:EzXHelper:1.0.3")
    implementation("org.lsposed.hiddenapibypass:hiddenapibypass:4.3")
    implementation("io.github.ranlee1:jpinyin:1.0.1")
    implementation("org.luckypray:DexKit:1.1.2")
    //UI
    implementation(project(":blockmiui"))
    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha07")
    //APP Center
    val appCenterSdkVersion = "5.0.0"
    implementation("com.microsoft.appcenter:appcenter-analytics:5.0.0")
    implementation("com.microsoft.appcenter:appcenter-crashes:5.0.0")
    implementation("androidx.core:core-splashscreen:1.0.0")
}
