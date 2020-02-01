package com.allever.android.dependency.lib

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.allever.lib.comment.CommentHelper
import com.allever.lib.comment.CommentListener
import com.allever.lib.common.app.BaseActivity
import com.allever.lib.common.util.DisplayUtils
import com.allever.lib.recommend.RecommendActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private lateinit var mIv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mIv = findViewById(R.id.main_iv)
//        ImageLoader.setProxy(GlideProxy())
//        ImageLoader.loadImage(this, "https://www.baidu.com/img/bd_logo1.png", mIv)


        btnRecommend.setOnClickListener {
            val channel = etChannel.text.toString()
            RecommendActivity.start(this, channel)
        }

        mCommentDialog = CommentHelper.create(this, object : CommentListener {
            override fun onComment(dialog: AlertDialog?) {
                dialog?.dismiss()
            }

            override fun onReject(dialog: AlertDialog?) {
                dialog?.dismiss()
            }

            override fun onBackPress(dialog: AlertDialog?) {
                mCommentDialog?.dismiss()
                GlobalScope.launch {
                    delay(200)
                    finish()
                }
            }
        })
    }

    private var mCommentDialog: AlertDialog? = null
    override fun onBackPressed() {
        if (mCommentDialog?.isShowing == true) {
            super.onBackPressed()
        } else {
            CommentHelper.show(this, mCommentDialog)
        }
    }
}
