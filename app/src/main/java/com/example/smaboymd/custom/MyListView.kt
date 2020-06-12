package com.example.smaboymd.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ListView
import org.jetbrains.anko.firstChild
import kotlin.math.abs

/**
 * 类名: MyListView
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/6/10 11:08 AM
 *
 */
class MyListView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    ListView(context, attrs, defStyleAttr, defStyleRes) {

    private var dX = 0f
    private var dY = 0f
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        /**
         * 思路分析：
         * 1.当ListView滑动到顶部，继续向下滑动的时候，将事件抛出去交给上层处理
         * 2.当ListView滑动到底部，继续向上滑动的时候，将事件抛出去交给上层处理
         *
         * 注意 childCount是当前显示的子类个数，当getChildAt(position)中，当position大于等于childCount时，返回为空
         * 注意 考虑到ListView是竖直滑动，因此我们将水平滑动的过滤掉
         */
        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                parent.requestDisallowInterceptTouchEvent(true)//不拦截
            }
            MotionEvent.ACTION_MOVE ->{
                if (firstVisiblePosition == 0 && getChildAt(0).top ==0 && ev.y - dY > 0) {
                    parent.requestDisallowInterceptTouchEvent(false)//拦截
                }else if (lastVisiblePosition == count-1  && getChildAt(childCount-1).bottom == height && ev.y - dY < 0) {
                    parent.requestDisallowInterceptTouchEvent(false)//拦截
                }

            }
            MotionEvent.ACTION_UP ->{
                parent.requestDisallowInterceptTouchEvent(true)//不拦截
            }
        }

        dX = ev?.x ?: 0f
        dY = ev?.y ?: 0f

//        return super.dispatchTouchEvent(ev)

        val b = super.dispatchTouchEvent(ev)
        Log.e("smaboy","MyListView-dispatchTouchEvent-$b")
        return b
    }


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        return super.onTouchEvent(ev)

        val b = super.onTouchEvent(ev)
        Log.e("smaboy","MyListView-onTouchEvent-$b")
        return b
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        return super.onInterceptTouchEvent(ev)

        val b = super.onInterceptTouchEvent(ev)
        Log.e("smaboy","MyListView-onInterceptTouchEvent-$b")
        return b
    }

}