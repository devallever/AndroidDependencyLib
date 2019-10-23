package com.allever.lib.permission

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.allever.lib.common.app.App
import com.yanzhenjie.permission.AndPermission

object PermissionManager {
    fun request(listener: PermissionListener, vararg permissions: String) {
        AndPermission.with(App.context)
            .runtime()
            .permission(permissions)
            .onGranted {
                listener.onGranted(it)
            }
            .onDenied {
                if (AndPermission.hasAlwaysDeniedPermission(App.context, it)) {
                    listener.alwaysDenied(it)
                } else {
                    listener.onDenied(it)
                }
            }
            .start()
    }

    fun hasPermissions(vararg permissions: String): Boolean {
        return PermissionCompat.hasPermission(App.context, *permissions)
        //下面这种方式会有意想不到的bug，例如通过这个判断后原来的相机会停止预览
        return AndPermission.hasPermissions(App.context, permissions)
    }


    fun jumpPermissionSetting(activity: Activity?, requestCode: Int, cancelListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.permission_permission_need_some_permission)
        builder.setTitle(R.string.permission_permission_warm_tips)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.permission_permission_go) { dialog, which ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity?.packageName, null)
            intent.data = uri
            activity?.startActivityForResult(intent, requestCode)
        }
        builder.setNegativeButton(R.string.permission_permission_cancel, cancelListener)
        builder.show()
    }

    @Deprecated("")
    fun jumpPermissionSetting() {
//        JumpSettingActivity.start(App.context)
    }
}