package com.allever.lib.recommend

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.allever.android.recyclerview.widget.MyLinearLayoutManager
import com.allever.android.recyclerview.widget.MyRecyclerView
import com.allever.lib.common.app.BaseActivity
import com.allever.lib.common.ui.widget.recycler.BaseViewHolder
import com.allever.lib.common.ui.widget.recycler.ItemListener
import com.allever.lib.common.util.SystemUtils
import com.allever.lib.common.util.log
import com.allever.lib.common.util.loge
import com.allever.lib.common.util.toast
import rx.Subscriber

class RecommendActivity: BaseActivity(), View.OnClickListener {

    private var mRecommendData = mutableListOf<Recommend>()
    private lateinit var mRvRecommendList: MyRecyclerView
    private var mAdapter: RecommendAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        findViewById<View>(R.id.iv_back).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_label).text = getString(R.string.recommend)

        mRecommendData.addAll(RecommendGlobal.recommendData)
        mAdapter = RecommendAdapter(this, R.layout.item_recommend, mRecommendData)
        mRvRecommendList = findViewById(R.id.rvRecommendList)
        mRvRecommendList.layoutManager = MyLinearLayoutManager(this)
        mRvRecommendList.adapter = mAdapter
        mAdapter?.setItemListener(object : ItemListener {
            override fun onItemClick(position: Int, holder: BaseViewHolder) {
                SystemUtils.openUrl(this@RecommendActivity, mRecommendData[position].url)
            }
        })

        if (RecommendGlobal.recommendData.isEmpty()) {
            getRecommendData()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

    private fun getRecommendData() {
        if (SystemUtils.isChineseLang()) {
            RetrofitUtil.getRecommendZh(object : Subscriber<RecommendBean>(){
                override fun onNext(data: RecommendBean?) {
                    handleRecommendData(data)
                }

                override fun onCompleted() {
                    log("获取推荐数据成功")
                }

                override fun onError(e: Throwable?) {
                    loge(e?.printStackTrace().toString())
                    log("获取推荐数据失败")
                    toast("暂时没有推荐数据")
                }
            })
        } else {
            RetrofitUtil.getRecommendEn(object : Subscriber<RecommendBean>(){
                override fun onNext(data: RecommendBean?) {
                    handleRecommendData(data)
                }

                override fun onCompleted() {
                    log("获取推荐数据成功")
                }

                override fun onError(e: Throwable?) {
                    loge(e?.printStackTrace().toString())
                    log("获取推荐数据失败")
                    toast("暂时没有推荐数据")
                }
            })
        }

    }

    private fun handleRecommendData(data: RecommendBean?) {
        if (data?.data?.isNotEmpty() == true) {
            RecommendGlobal.recommendData.clear()
            RecommendGlobal.recommendData.addAll(data.data!!)
            mRecommendData.clear()
            mRecommendData.addAll(data.data!!)
            mAdapter?.notifyDataSetChanged()
        }
    }
}