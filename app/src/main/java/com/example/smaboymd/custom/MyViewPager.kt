package com.example.smaboymd.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

/**
 * 类名: MyViewPager
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/6/11 7:55 PM
 *
 */
class MyViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    private var dX = 0f
    private var dY = 0f
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //内部拦截

        /**
         * 思路分析：
         * 当触点处于在view内，交给该view处理
         *
         * 1.上层的down事件能传递下来，后面的事件被拦截了，而viewpager我们需要在该控件处理,在接收down事件时，让上层放开拦截;
         * 2.由于上册布局是上下滑动的，因此当我viewpager中存在上下滑动的控件时，需要viewpager的内部view、能够上下滑动，到达滑动极限时,
         *   把滑动交给viewpager的外层(即上层)处理
         *
         */
        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                parent.requestDisallowInterceptTouchEvent(true)//不拦截
            }
            MotionEvent.ACTION_MOVE ->{
                if (abs(ev.y - dY) - abs(ev.x - dX) > 0 && currentItem != adapter?.count?.minus(1) ?: 0) parent.requestDisallowInterceptTouchEvent(false)//拦截

            }
            MotionEvent.ACTION_UP ->{
                parent.requestDisallowInterceptTouchEvent(true)//不拦截
            }
        }

        dX = ev?.x ?: 0f
        dY = ev?.y ?: 0f

        val b = super.dispatchTouchEvent(ev)
        Log.e("smaboy","MyViewPager-dispatchTouchEvent-$b")
        return b
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val b = ev?.action != MotionEvent.ACTION_DOWN
        Log.e("smaboy","MyViewPager-onInterceptTouchEvent-$b")
        return b
    }


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        val b = super.onTouchEvent(ev)
        Log.e("smaboy","MyViewPager-onTouchEvent-$b")
        return b
    }

}