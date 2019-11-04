package com.allever.lib.common.util

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.allever.lib.common.R

object ShareHelper {

    fun shareText(obj: Any, msg: String) {
        try {
            when(obj) {
                is Fragment -> {
                    obj.startActivity(getShareIntent(msg))
                }
                is android.app.Fragment -> {
                    obj.startActivity(getShareIntent(msg))
                }
                is Activity -> {
                    obj.startActivity(getShareIntent(msg))
                }
            }
        } catch (e: Exception) {
            toast("没有找到可分享的应用")
            e.printStackTrace()
        }
    }

    private fun getShareIntent(msg: String): Intent? {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_TEXT, msg)
        shareIntent.type = "text/plain"
        return Intent.createChooser(
            shareIntent,
            getString(R.string.common_share_to)
        )
    }

}