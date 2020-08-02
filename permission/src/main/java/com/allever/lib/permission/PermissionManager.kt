package com.allever.lib.permission

import android.app.Activity
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.allever.lib.common.app.App

object PermissionManager {
    fun request(
        activity: Activity,
        listener: PermissionListener,
        vararg permissions: String
    ) {
        FastPermission.request(activity, listener, *permissions)
    }

    fun request(fragment: Fragment, listener: PermissionListener, vararg permissions: String) {
        FastPermission.request(fragment, listener, *permissions)
    }

    fun hasPermissions(vararg permissions: String): Boolean {
        return FastPermission.hasPermissions(App.context, *permissions)
    }

    fun alwaysDenyPermissions(activity: Activity, vararg permissions: String): Boolean =
        FastPermission.hasAlwaysDeniedPermission(activity, *permissions)

    fun jumpPermissionSetting(
        activity: Activity?,
        requestCode: Int,
        cancelListener: DialogInterface.OnClickListener
    ) {
        FastPermission.openPermissionManually(activity, requestCode, cancelListener)
    }
}