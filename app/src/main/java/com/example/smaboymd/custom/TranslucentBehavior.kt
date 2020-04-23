package com.example.smaboymd.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView


/**
 * 类名: TranslucentBehavior
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/4/23 4:05 PM
 *
 */
class TranslucentBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    // 列表顶部和title底部重合时，列表的滑动距离。
    private var deltaY = 0f

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is NestedScrollView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (deltaY == 0f) {
            deltaY = dependency.y - child.height
        }
        var dy = dependency.y - child.height
        dy = if (dy < 0) 0f else dy
        val y = -(dy / deltaY) * child.height
        child.translationY = y
        return true
    }
//    fun layoutDependsOn(
//        parent: CoordinatorLayout?,
//        child: View?,
//        dependency: View?
//    ): Boolean {
//        return dependency is RecyclerView
//    }
//
//    fun onDependentViewChanged(
//        parent: CoordinatorLayout?,
//        child: View,
//        dependency: View
//    ): Boolean {
//        if (deltaY == 0f) {
//            deltaY = dependency.y - child.height
//        }
//        var dy = dependency.y - child.height
//        dy = if (dy < 0) 0 else dy
//        val y = -(dy / deltaY) * child.height
//        child.translationY = y
//        return true
//    }

}