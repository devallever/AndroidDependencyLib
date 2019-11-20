package com.allever.android.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class MyViewPager: ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    interface OnPageChangeListener: ViewPager.OnPageChangeListener

}