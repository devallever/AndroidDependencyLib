package com.allever.lib.comment

import androidx.appcompat.app.AlertDialog

interface CommentListener {

    fun onComment(dialog: AlertDialog?)

    fun onReject(dialog: AlertDialog?)

    fun onBackPress(dialog: AlertDialog?)
}