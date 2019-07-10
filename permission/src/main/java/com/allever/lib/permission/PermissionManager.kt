package com.allever.lib.permission

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
}