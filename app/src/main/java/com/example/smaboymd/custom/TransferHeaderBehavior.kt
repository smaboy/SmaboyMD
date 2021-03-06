package com.example.smaboymd.custom

import android.R.attr
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import com.gyf.immersionbar.ImmersionBar
import org.jetbrains.anko.dip
import kotlin.math.roundToInt


/**
 * 类名: TransferHeaderBehavior
 * 类作用描述: 头像view的移动和放大缩小
 * 作者: liyongliang3
 * 创建时间: 2020/4/23 7:28 PM
 *
 */
class TransferHeaderBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    /**
     * 处于中心时候原始X轴
     */
    private var mOriginalHeaderX = 0

    /**
     * 处于中心时候原始Y轴
     */
    private var mOriginalHeaderY = 0


    private  var mContext : Context? = null

    init {
        mContext = context

    }

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
        if (mContext == null) {
            return super.onDependentViewChanged(parent, child, dependency)
        }
        // 计算X轴坐标
        if (mOriginalHeaderX == 0) {
            mOriginalHeaderX = dependency.width / 2 - child.width / 2+mContext!!.dip(10)
        }
        // 计算Y轴坐标
        if (mOriginalHeaderY == 0) {
            //依赖布局dependency的纵坐标减去imageview的高度一般加上30的偏移量
            mOriginalHeaderY = (dependency.y - child.height/2 - mContext!!.dip(15) ).toInt()
        }

        //X轴百分比
        var mPercentX: Float = dependency.y / mOriginalHeaderX
        if (mPercentX >= 1) {
            mPercentX = 1f
        }
        //Y轴百分比
        var mPercentY: Float = dependency.y / mOriginalHeaderY
        if (mPercentY >= 1) {
            mPercentY = 1f
        }

        var x: Float = mOriginalHeaderX - mOriginalHeaderX * mPercentX
        if (x <= 0) {
            x = 0F
        }

        // 设置child位置(注：由于child缩小了一倍，默认是左上顶点0，0；考虑状态栏的高度和标题栏的高度，添加了后面的位移，保持头像能在标题栏的竖直居中的位置，左边距为10dp)
        child.x = x-mContext!!.dip(10)
        child.y = mOriginalHeaderY - mOriginalHeaderY * mPercentY+(-mContext!!.dip(10)+ImmersionBar.getStatusBarHeight(mContext as Activity))

        //如需要精确放置头像的位置，需确定头像在标题栏具体坐标

        //设置child的缩放g
        child.scaleX = (1-0.5*mPercentX).toFloat()
        child.scaleY = (1-0.5*mPercentY).toFloat()

        Log.e("smaboy","name==${TransferHeaderBehavior::class.simpleName}---dependencyY==${dependency.y}----childWidth==${child.width}---childHeight==${child.height}---childX==${child.x}---childY==${child.y}")
        return true
    }
}