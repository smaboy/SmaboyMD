package com.example.smaboymd.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView


/**
 * 类名: TranslucentBehavior
 * 类作用描述: 标题栏的展示，实现和移动控件的表现交互，如标题栏的从上到下的平移透明度的设置
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
        //设置y轴方向的移动
        if (deltaY == 0f) {
            deltaY = dependency.y - child.height
        }
        var dy = dependency.y - child.height
        dy = if (dy < 0) 0f else dy

        //设置child在y轴的位置，实现y轴方向上的平移
        val y = -(dy / deltaY) * child.height
        child.translationY = y

        //设置透明度
        child.background.mutate().alpha = (255 * (deltaY-dy)/deltaY).toInt()

        Log.e("smaboy","name==${TranslucentBehavior::class.simpleName}---dependencyY==${dependency.y}----childHeight==${child.height}---childY==${child.y}---childAlpha==${child.alpha}")
        return true
    }
}