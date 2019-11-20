package com.allever.android.widget

import android.content.Context
import android.support.v7.widget.SwitchCompat
import android.util.AttributeSet

class MySwitchCompat: SwitchCompat {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}