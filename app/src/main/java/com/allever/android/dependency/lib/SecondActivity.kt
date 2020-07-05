package com.allever.android.dependency.lib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.allever.lib.common.app.BaseActivity

class SecondActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun start(context: Context, id: Int, name: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.apply {
                putExtra("id", id)
                putExtra("name", name)
            }
            context.startActivity(intent)
        }
    }
}