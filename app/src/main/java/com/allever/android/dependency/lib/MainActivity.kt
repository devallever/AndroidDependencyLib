package com.allever.android.dependency.lib

import android.animation.*
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.allever.lib.comment.CommentDialog
import com.allever.lib.comment.CommentHelper
import com.allever.lib.comment.CommentListener
import com.allever.lib.common.app.BaseActivity
import com.allever.lib.common.util.toast
import com.allever.lib.recommend.RecommendActivity
import com.allever.lib.recommend.RecommendDialogHelper
import com.allever.lib.recommend.RecommendDialogListener
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var mIv: ImageView

    private var mAdAnimA: ObjectAnimator? = null
    private var mAdAnimB:ObjectAnimator? = null
    private var mAdAnimC:ObjectAnimator? = null
    private var mAdAnimD:ObjectAnimator? = null
    private var mImageAda: ImageView? = null
    private var mImageAdb:ImageView? = null
    private var mImageAdc:ImageView? = null
    private var mImageAdd:ImageView? = null
    private val mObjAnimatorList = ArrayList<ObjectAnimator>()
    private var mRandom: Random? = null
//    private var mAdModelList: List<AdModel>? = LinkedList<AdModel>()
    private val mIvList = ArrayList<ImageView>()
    private var recommendLayout: LinearLayout? = null

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

        val shimmerLayout = findViewById<ShimmerFrameLayout>(R.id.shimmerLayout)
        val shimmer = Shimmer.AlphaHighlightBuilder()
        shimmer.setDuration(1500)
//        shimmer.setBaseAlpha(0.9f)
        shimmerLayout.setShimmer(shimmer.build())
        shimmerLayout.startShimmer()

        mCommentDialog = CommentHelper.createCommentDialog(this, object : CommentListener {
            override fun onComment(dialog: Dialog?) {
                dialog?.dismiss()
            }

            override fun onReject(dialog: Dialog?) {
                dialog?.dismiss()
            }

            override fun onBackPress(dialog: Dialog?) {
                mCommentDialog?.dismiss()
                GlobalScope.launch {
                    delay(200)
                    finish()
                }
            }
        }) as CommentDialog


        mImageAda = findViewById(R.id.iv1)
        mImageAdb = findViewById(R.id.iv2)
        mImageAdc = findViewById(R.id.iv3)
        mImageAdd = findViewById(R.id.iv4)
        recommendLayout = findViewById(R.id.llRecommend)
        mIvList.add(mImageAda!!)
        mIvList.add(mImageAdb!!)
        mIvList.add(mImageAdc!!)
        mIvList.add(mImageAdd!!)

        mRandom = Random()

        //初始化动画资源
        initAnimation()

        start(mObjAnimatorList[mRandom?.nextInt(4)!!])


        val list = ArrayList<String>()

        list.add("3333")
        list.add("1")
        list.add("22222222222222222222222222222222222222222222")
        list.add("清空万里")
        list.add("两条相交线")
        list.add("短途")
        list.add("希望在明天会更好")

        val autoAdapter = MyAutoAdapter(this)
        autoAdapter.setOnItemClickListener { view, text ->
            toast(text)
        }
        autoAdapter.setData(list)
        autoLayout.setAdapter(autoAdapter)

        val url = "https://shouji.baidu.com/software/26799705.html"
        recommendImageView.setRecommendData(R.drawable.ic_logo, "com.allever.app.virtual.call", url)

    }

    override fun onResume() {
        super.onResume()
        recommendImageView.refresh()
    }

    private var mCommentDialog: Dialog? = null
    override fun onBackPressed() {
        if (mCommentDialog?.isShowing == true) {
            super.onBackPressed()
        } else {
//            mCommentDialog?.show()

            val recommendDialog = RecommendDialogHelper.createRecommendDialog(this, object : RecommendDialogListener {
                override fun onMore(dialog: Dialog?) {
                    dialog?.dismiss()
                }

                override fun onReject(dialog: Dialog?) {
                    dialog?.dismiss()
                }

                override fun onBackPress(dialog: Dialog?) {
                    mCommentDialog?.dismiss()
                    GlobalScope.launch {
                        delay(200)
                        finish()
                    }
                }
            })
            RecommendDialogHelper.show(this, recommendDialog)
        }
    }


    private fun initAnimation() {
        mAdAnimA = setAnim(mImageAda, 1f, 2000, 1500)
        mAdAnimB = setAnim(mImageAdb, 1f, 2000, 1500)
        mAdAnimC = setAnim(mImageAdc, 1f, 2000, 1500)
        mAdAnimD = setAnim(mImageAdd, 1f, 2000, 1500)
        mObjAnimatorList.add(mAdAnimA!!)
        mObjAnimatorList.add(mAdAnimB!!)
        mObjAnimatorList.add(mAdAnimC!!)
        mObjAnimatorList.add(mAdAnimD!!)
    }

    private fun setAnim(
        view: View?,
        shakeFactor: Float,
        startDelay: Int,
        duration: Int
    ): ObjectAnimator {
        val pvhRotate = PropertyValuesHolder.ofKeyframe(
            View.ROTATION,
            Keyframe.ofFloat(0f, 0f),
            Keyframe.ofFloat(.1f, -3f * shakeFactor),
            Keyframe.ofFloat(.2f, -3f * shakeFactor),
            Keyframe.ofFloat(.3f, 3f * shakeFactor),
            Keyframe.ofFloat(.4f, -3f * shakeFactor),
            Keyframe.ofFloat(.5f, 3f * shakeFactor),
            Keyframe.ofFloat(.6f, -3f * shakeFactor),
            Keyframe.ofFloat(.7f, 3f * shakeFactor),
            Keyframe.ofFloat(.8f, -3f * shakeFactor),
            Keyframe.ofFloat(.9f, 3f * shakeFactor),
            Keyframe.ofFloat(1f, 0f)
        )

        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, pvhRotate)
        objectAnimator.duration = duration.toLong()
        objectAnimator.startDelay = startDelay.toLong()
        return objectAnimator
    }

    private fun start(objectAnimator: ObjectAnimator) {
        val animatorSet = AnimatorSet()
        animatorSet.play(objectAnimator)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                start(mObjAnimatorList[mRandom?.nextInt(4)!!])
            }
        })
        animatorSet.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdAnimA?.cancel()
        mAdAnimB?.cancel()
        mAdAnimC?.cancel()
        mAdAnimD?.cancel()
    }
}
