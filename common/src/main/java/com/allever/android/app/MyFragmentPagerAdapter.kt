package com.allever.android.app

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

abstract class MyFragmentPagerAdapter: FragmentPagerAdapter {
    constructor(fm: FragmentManager?) : super(fm)
}