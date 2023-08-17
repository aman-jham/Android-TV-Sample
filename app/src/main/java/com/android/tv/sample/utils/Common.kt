package com.android.tv.sample.utils

import android.content.Context

object Common {


    fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels
        return (width * percent) / 100
    }


}