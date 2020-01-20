package com.allever.lib.recommend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.allever.android.recyclerview.widget.MyLinearLayoutManager
import com.allever.android.recyclerview.widget.MyRecyclerView
import com.allever.lib.common.app.App
import com.allever.lib.common.app.BaseActivity
import com.allever.lib.common.ui.widget.recycler.BaseViewHolder
import com.allever.lib.common.ui.widget.recycler.ItemListener
import com.allever.lib.common.util.*
import com.google.gson.Gson
import rx.Subscriber
import java.io.File

class RecommendActivity : BaseActivity(), View.OnClickListener {

    private var mRecommendData = mutableListOf<Recommend>()
    private lateinit var mRvRecommendList: MyRecyclerView
    private var mAdapter: RecommendAdapter? = null
    private var mUmengChannel = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        mUmengChannel = intent?.getStringExtra(EXTRA_CHANNEL) ?: ""

        findViewById<View>(R.id.iv_back).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_label).text = getString(R.string.recommend)

        mRecommendData.addAll(RecommendGlobal.recommendData)
        mAdapter = RecommendAdapter(this, R.layout.item_recommend, mRecommendData)
        mRvRecommendList = findViewById(R.id.rvRecommendList)
        mRvRecommendList.layoutManager = MyLinearLayoutManager(this)
        mRvRecommendList.adapter = mAdapter
        mAdapter?.setItemListener(object : ItemListener {
            override fun onItemClick(position: Int, holder: BaseViewHolder) {
                //如果安装了谷歌商店，则打开google商店
                val url = when (mUmengChannel) {
                    "google" -> {
                        mRecommendData[position].googleUrl
                    }
                    "xiaomi" -> {
                        mRecommendData[position].xiaomiUrl
                    }
                    else -> {
                        mRecommendData[position].url
                    }
                }
                SystemUtils.openUrl(this@RecommendActivity, url)
            }
        })

        if (RecommendGlobal.recommendData.isEmpty()) {
            getRecommendData()
        }

//        if (BuildConfig.DEBUG) {
//            getLocalRecommendData()
//        } else {
//            if (RecommendGlobal.recommendData.isEmpty()) {
//                getRecommendData()
//            }
//        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        setResult(Activity.RESULT_OK)
    }

    private fun getLocalRecommendData() {
        val path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "recommend.json"
        val recommendData = FileUtils.readTextFile(path)
        try {
            val gson = Gson()
            val recommend = gson.fromJson(recommendData, RecommendBean::class.java)
            handleRecommendData(recommend)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getRecommendData() {
        val subscriber = object : Subscriber<RecommendBean>() {
            override fun onNext(data: RecommendBean?) {
                handleRecommendData(data)
            }

            override fun onCompleted() {
                log("获取推荐数据成功")
            }

            override fun onError(e: Throwable?) {
                loge(e?.message?:"")
                log("获取推荐数据失败")
                toast("No Recommend")
            }
        }
        if (SystemUtils.isChineseLang()) {
            RetrofitUtil.getRecommendZh(subscriber)
        } else {
            RetrofitUtil.getRecommendEn(subscriber)
        }

    }

    private fun handleRecommendData(data: RecommendBean?) {
        if (data?.data?.isNotEmpty() == true) {
            mRecommendData.clear()

            when(mUmengChannel) {
                "google" -> {
                    handleChannelRecommendData(data, "google")
                }
                "xiaomi" -> {
                    handleChannelRecommendData(data, "xiaomi")
                }
                else -> {
                    data.data?.map {
                        if (it.url.isNotEmpty()) {
                            mRecommendData.add(it)
                        }
                    }
//                    mRecommendData.addAll(data.data!!)
                }
            }
            mAdapter?.notifyDataSetChanged()

            RecommendGlobal.recommendData.clear()
            RecommendGlobal.recommendData.addAll(mRecommendData)
        }
    }

    private fun handleChannelRecommendData(data: RecommendBean?, channel: String) {
        data?.data?.map {
            val channelValue = it.channel
            if (!TextUtils.isEmpty(channelValue)) {
                if (channelValue.contains("|")) {
                    // | 分割的多个渠道
                    val channelList = channelValue.split("|")
                    if (channelList.contains(channel)) {
                        mRecommendData.add(it)
                    }
                } else {
                    //单个渠道值
                    if (channelValue == channel) {
                        mRecommendData.add(it)
                    }
                }
            }
        }
    }

    companion object {
        private const val EXTRA_CHANNEL = "EXTRA_CHANNEL"
        fun start(context: Context, channel: String) {
            val intent = Intent(context, RecommendActivity::class.java)
            intent.putExtra(EXTRA_CHANNEL, channel)
            context.startActivity(intent)
        }
    }
}