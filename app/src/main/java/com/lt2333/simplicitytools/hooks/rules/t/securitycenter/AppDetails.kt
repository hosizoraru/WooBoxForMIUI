package com.lt2333.simplicitytools.hooks.rules.t.securitycenter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.verify.domain.DomainVerificationManager
import android.os.Build
import android.view.View
import android.widget.TextView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import com.lt2333.simplicitytools.BuildConfig
import com.lt2333.simplicitytools.R
import com.lt2333.simplicitytools.utils.chsbuffer.*
import com.lt2333.simplicitytools.utils.hasEnable
import io.luckypray.dexkit.DexKitBridge
import io.luckypray.dexkit.enums.FieldUsingType
import io.luckypray.dexkit.enums.MatchType

class AppDetails(val dexKit: DexKitBridge, val app: Application) : Hook() {
    val domainVerificationManager: DomainVerificationManager by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            app.getSystemService(
                DomainVerificationManager::class.java
            )
        } else {
            null!!
        }
    }
    val moduleContext: Context by lazy {
        app.createPackageContext(
            BuildConfig.APPLICATION_ID, 0
        )
    }

    @SuppressLint("DiscouragedApi", "PrivateApi")
    override fun init(classLoader: ClassLoader) {

        /** *是否是系统应用*字段 */
        val isSystemAppField = dexKit.findMethodUsingField {
            fieldType = "Z"
            usingType = FieldUsingType.PUT
            callerMethodDeclareClass = "Lcom/miui/appmanager/ApplicationsDetailsActivity;"
            callerMethodName = "initView"
            callerMethodReturnType = "V"
        }.values.single().single().name
//  this.o0 = (applicationInfo.flags & 1) != 0

        /** *LiveData* 读取后 View更新 方法 */
        val appDetailOnLoadDataFinishMethodDesc = dexKit.findMethodUsingString {
            usingString = "enter_way"
            matchType = MatchType.CONTAINS
            methodDeclareClass = "Lcom/miui/appmanager/ApplicationsDetailsActivity;"
            methodReturnType = "void"
            methodParamTypes = arrayOf("", "Ljava/lang/Boolean;")
        }.single()
        val appDetailOnLoadDataFinishMethod =
            appDetailOnLoadDataFinishMethodDesc.getMethodInstance(classLoader)
//  public void a(a.j.b.c<Boolean> cVar, Boolean bool) {                      // <- a
//      ……
//      if (this.k0) {
//          appDetailTextBannerView = this.p;
//          i2 = R.string.app_manager_default_open_summary;
//      } else {
//          appDetailTextBannerView = this.p;
//          i2 = R.string.app_manager_default_close_summary;
//      }

        /** *联网控制*按钮生成概括文本的方法 */
        val getNetCtrlSummaryMethod = dexKit.findMethodInvoking {
            methodDescriptor = appDetailOnLoadDataFinishMethodDesc.descriptor
            beInvokedMethodDeclareClass = "Lcom/miui/appmanager/ApplicationsDetailsActivity;"
            beInvokedMethodReturnType = "Ljava/lang/String;"
            beInvokedMethodParameterTypes = arrayOf()
        }[appDetailOnLoadDataFinishMethodDesc]!!.single().getMethodInstance(classLoader)
//  this.m.setSummary(L());               // <- L
//  ...
//  L() {
//      if (this.o0) {                    // o0 isSystemApp
//          i2 = C0412R.string.app_manager_system_mobile_disable;
//      } else if (!this.q0) {
//          i2 = C0412R.string.app_manager_disable;
//      }

        /** *联网控制* 复选按钮 *点击事件* 处理 方法 */
        val netCtrlShowDialogMethod = dexKit.findMethodUsingOpCodeSeq {
            opSeq = intArrayOf(0x55, 0x5c, 0x55, 0x5c, 0x55, 0x5c)
            methodDeclareClass = "Lcom/miui/appmanager/ApplicationsDetailsActivity;"
            methodReturnType = "V"
            methodParamTypes = arrayOf()
        }.single().getMethodInstance(classLoader)
//  Z();                                      // <- Z
//  str = "network_control";

        val net_id = app.resources.getIdentifier(
            "am_detail_net", "id", app.packageName
        )

        val default_id = app.resources.getIdentifier(
            "am_detail_default", "id", app.packageName
        )

        val appDetailClz =
            XposedHelpers.findClass("com.miui.appmanager.ApplicationsDetailsActivity", classLoader)

        /* "联网控制"对话框确定 onClick */
        val saveNetCtrlDialogOnClickMethod by lazy {
            appDetailClz.declaredClasses.first {
                DialogInterface.OnMultiChoiceClickListener::class.java.isAssignableFrom(it)
            }.methods.first {
                it.name == "onClick"
            }
        }

        hasEnable("original_default_open_setting") {
            // 修改“清除默认操作”点击打开“默认打开”
            XposedHelpers.findAndHookMethod(
                appDetailClz,
                "onClick",
                android.view.View::class.java,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        val clickedView = param.args[0]
                        val cleanDefaultView =
                            (param.thisObject as Activity).findViewById<View>(default_id)
                        val pkgName =
                            (param.thisObject as Activity).intent.getStringExtra("package_name")!!

                        if (clickedView == cleanDefaultView) {
                            val intent = Intent().apply {
                                action =
                                    android.provider.Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS
                                addCategory(Intent.CATEGORY_DEFAULT)
                                data = android.net.Uri.parse("package:${pkgName}")
                                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                                addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                            }
                            XposedHelpers.callMethod(
                                param.thisObject, "startActivity", intent
                            )
                            param.result = null
                        }
                    }
                })

            // 加载完毕数据后，修改“清除默认操作”按钮标题和描述为“默认打开”
            XposedBridge.hookMethod(appDetailOnLoadDataFinishMethod, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam) {
                    val cleanDefaultView =
                        (param.thisObject as Activity).findViewById<View>(default_id)
                    val pkgName =
                        (param.thisObject as Activity).intent.getStringExtra("package_name")!!

                    val isLinkHandlingAllowed =
                        domainVerificationManager.getDomainVerificationUserState(
                            pkgName
                        )?.isLinkHandlingAllowed ?: false
                    val subTextId =
                        if (isLinkHandlingAllowed) R.string.app_link_open_always else R.string.app_link_open_never

                    // set title
                    // 因为 AppDetailTextBannerView 没有 setTitle 方法，
                    // 所以先将分别作为 Title 和 Summary 的两个 TextView 的文本都设为 "Open by default"
                    // 之后再调用 setSummary 设置 Summary 的 TextView
                    cleanDefaultView::class.java.declaredFields.forEach {
                        val textView = XposedHelpers.getObjectField(cleanDefaultView, it.name)
                        if (textView !is TextView) return@forEach

                        XposedHelpers.callMethod(
                            textView,
                            "setText",
                            arrayOf(CharSequence::class.java),
                            moduleContext.getString(R.string.open_by_default)
                        )
                    }

                    // set summary
                    XposedHelpers.callMethod(
                        cleanDefaultView, "setSummary", moduleContext.getString(subTextId)
                    )
                }
            })
        }

//        if (xPrefs.getBoolean(
//                "original_default_open_setting", true
//            ) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
//        ) {
//
//        }

        hasEnable("system_app_wlan_control") {
            XposedHelpers.findAndHookConstructor(appDetailClz, object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    // 和 联网控制 有关的方法调用期间，将 isSystemApp 设为 false
                    val hook = BooleanDuringMethod(param.thisObject, isSystemAppField, false)

                    XposedBridge.hookMethod(
                        saveNetCtrlDialogOnClickMethod, hook
                    )
                    XposedBridge.hookMethod(getNetCtrlSummaryMethod, hook)
                    XposedBridge.hookMethod(netCtrlShowDialogMethod, hook)
                }
            })

            // 仅WIFi设备会直接隐藏联网控制
            if (XposedHelpers.callStaticMethod(
                    classLoader.loadClass("android.os.SystemProperties"),
                    "getBoolean",
                    "ro.radio.noril",
                    false
                ) as Boolean
                || BuildConfig.DEBUG
            ) {
                val net_ctrl_title_id = app.resources.getIdentifier(
                    "app_manager_net_control_title", "string", app.packageName
                )
                val app_manager_disable = app.getString(
                    app.resources.getIdentifier(
                        "app_manager_disable", "string", app.packageName
                    )
                )

                XposedBridge.hookMethod(appDetailOnLoadDataFinishMethod, object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        val pkgName =
                            (param.thisObject as Activity).intent.getStringExtra("package_name")!!
                        val allowInternet = app.packageManager.checkPermission(
                            "android.permission.INTERNET", pkgName
                        ) == PackageManager.PERMISSION_GRANTED

                        if (allowInternet && pkgName != "com.xiaomi.finddevice" && pkgName != "com.miui.mishare.connectivity") {
                            val netCtrlView =
                                (param.thisObject as Activity).findViewById<View>(net_id)
                            netCtrlView.visibility = View.VISIBLE
                            XposedHelpers.callMethod(netCtrlView, "setTitle", net_ctrl_title_id)
                        }
                    }
                })

                XposedBridge.hookMethod(getNetCtrlSummaryMethod, object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        if ((param.result as String).isBlank())
                            param.result = app_manager_disable
                    }
                })
        }

//        if (xPrefs.getBoolean("system_app_wlan_control", true)) {
//
//            }
        }
    }
}