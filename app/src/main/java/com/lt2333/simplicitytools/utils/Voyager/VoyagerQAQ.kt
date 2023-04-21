package com.lt2333.simplicitytools.utils.Voyager

import android.os.Build
import com.github.kyuubiran.ezxhelper.utils.*
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File

object VoyagerQAQ {
    fun XC_LoadPackage.LoadPackageParam.getAppVersionCode(packageName: String): Int {
        return try {
            if (packageName == "android") {
                return Build.VERSION.SDK_INT
            }
            val parser = loadClass("android.content.pm.PackageParser").newInstance()
            val apkPath = File(this.appInfo.sourceDir)
            val pkg = parser.invokeMethod(
                "parsePackage",
                args(apkPath, 0),
                argTypes(File::class.java, Int::class.java)
            )
            pkg?.javaClass?.field("mVersionCode")?.getInt(pkg) ?: 0
        } catch (e: Throwable) {
            0
        }
    }

    fun XC_LoadPackage.LoadPackageParam.getAppVersionName(packageName: String): String {
        return try {
            if (packageName == "android") {
                return Build.VERSION.RELEASE_OR_CODENAME
            }
            val parser = loadClass("android.content.pm.PackageParser").newInstance()
            val apkPath = File(this.appInfo.sourceDir)
            val pkg = parser.invokeMethod(
                "parsePackage",
                args(apkPath, 0),
                argTypes(File::class.java, Int::class.java)
            )
            (pkg?.javaClass?.field("mVersionName")?.get(pkg) ?: "Error: Unknown") as String
        } catch (e: Throwable) {
            "Error: Unknown"
        }
    }

//    fun getVersionCode(context: Context, packageName: String): Long {
//        return try {
//            val packageInfo = context.packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
//            packageInfo.longVersionCode
//        } catch (e: PackageManager.NameNotFoundException) {
//            // 当应用包名不存在时返回 -1
//            -1L
//        }
//    }
//    fun getVersionName(context: Context, packageName: String): String? {
//        return try {
//            val packageInfo = context.packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
//            packageInfo.versionName
//        } catch (e: PackageManager.NameNotFoundException) {
//            // 当应用包名不存在时返回 null
//            null
//        }
//    }
//    fun versionNameToLong(versionName: String): Long {
//        val parts = versionName.split(".")
//        var weight = 1L
//        var result = 0L
//        for (part in parts.reversed()) {
//            result += part.toLong() * weight
//            weight *= 1000L
//        }
//        return result
//    }

}