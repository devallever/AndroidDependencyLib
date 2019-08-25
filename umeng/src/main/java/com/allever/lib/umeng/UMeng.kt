package com.allever.lib.umeng

import android.content.Context
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

object UMeng {
    fun init(context: Context, appKey: String, channel: String = "google", auto: Boolean = true) {
        UMConfigure.init(context, appKey, channel, UMConfigure.DEVICE_TYPE_PHONE, null)
        if (auto) {
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
        } else {
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL)
        }
    }
}