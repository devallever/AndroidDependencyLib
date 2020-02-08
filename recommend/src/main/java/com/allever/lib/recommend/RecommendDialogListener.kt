package com.allever.lib.recommend

import androidx.appcompat.app.AlertDialog

interface RecommendDialogListener {

    fun onMore(dialog: AlertDialog?)

    fun onReject(dialog: AlertDialog?)

    fun onBackPress(dialog: AlertDialog?)
}