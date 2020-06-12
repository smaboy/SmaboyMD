package com.example.smaboymd.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import kotlin.math.abs

/**
 * 类名: MyScrollView
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/6/10 2:43 PM
 *
 */
class MyHorizontalScrollView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    HorizontalScrollView(context, attrs, defStyleAttr) {
    private var dX = 0f
    private var dY = 0f
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                parent.requestDisallowInterceptTouchEvent(true)//不拦截
            }
            MotionEvent.ACTION_MOVE ->{
                if (abs(ev.y - dY) - abs(ev.x - dX) >0) parent.requestDisallowInterceptTouchEvent(false)//拦截
            }
            MotionEvent.ACTION_UP ->{
                parent.requestDisallowInterceptTouchEvent(true)//不拦截
            }
        }

        dX = ev?.x ?: 0f
        dY = ev?.y ?: 0f

        return super.dispatchTouchEvent(ev)

    }

}