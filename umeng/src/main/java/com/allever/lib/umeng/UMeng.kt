package com.allever.lib.umeng

import android.content.Context
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

object UMeng {
    /***
     * @param appKey 如果在Manifest配置了，传null
     * @param channel 如果在Manifest配置了，传null
     */
    fun init(context: Context, appKey: String? = null, channel: String? = null, auto: Boolean = true) {
        UMConfigure.init(context, appKey, channel, UMConfigure.DEVICE_TYPE_PHONE, null)
        if (auto) {
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
        } else {
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL)
        }
    }
}