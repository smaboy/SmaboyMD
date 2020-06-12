package com.example.smaboymd.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ListView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

/**
 * 类名: MyRecycler
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/6/10 2:14 PM
 *
 */
class MyRecycler @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RecyclerView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        super.onInterceptTouchEvent(e)
//        return e?.action != MotionEvent.ACTION_DOWN
        val b = e?.action != MotionEvent.ACTION_DOWN
        Log.e("smaboy","MyRecycler-onInterceptTouchEvent-$b")
        return b
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        val b = super.onTouchEvent(e)
        Log.e("smaboy","MyRecycler-onTouchEvent-$b")
        return b
//        return super.onTouchEvent(e)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val b = super.dispatchTouchEvent(ev)
        Log.e("smaboy","MyRecycler-dispatchTouchEvent-$b")
        return b
//        return super.dispatchTouchEvent(ev)
    }
}