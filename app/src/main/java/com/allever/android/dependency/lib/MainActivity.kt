package com.allever.android.dependency.lib

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.allever.android.dependency.lib.glide.GlideProxy
import com.allever.lib.imageloader.ImageLoader
import com.allever.lib.recommend.RecommendActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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


    }
}
