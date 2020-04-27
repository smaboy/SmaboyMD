package com.example.smaboymd.custom

import android.app.Activity
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.smaboymd.R
import com.gyf.immersionbar.ImmersionBar
import org.jetbrains.anko.find

/**
 * 类名: TitleBarView
 * 类作用描述: 标题栏自定义view
 * 作者: liyongliang3
 * 创建时间: 2020/4/26 1:09 PM
 *
 */
class TitleBarView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    /**
     * 点击监听接口
     */
    interface OnClickTitleBarViewListener{
        fun onLeftIconClick(view : View)
        fun onCenterTextClick(view : View)
        fun onRightIconClick(view : View)
    }

    /**
     * 监听接口对象
     */
    var onClickTitleBarViewListener : OnClickTitleBarViewListener? = null

    init {
        //将view添加进去
        LayoutInflater.from(context).inflate(R.layout.view_title_bar,this)

        //设置监听
        setListener()

        //适配状态栏，放置状态栏和标题栏重叠
        ImmersionBar.setStatusBarView(context as Activity,find<View>(R.id.v_status_bar))
    }

    /**
     * 设置各控件的监听
     */
    private fun setListener() {
        //左边图标点击事件
        find<ImageView>(R.id.iv_left_icon).setOnClickListener {
            onClickTitleBarViewListener?.onLeftIconClick(it)
        }
        //中间标题点击事件
        find<TextView>(R.id.tv_center_title).setOnClickListener {
            onClickTitleBarViewListener?.onCenterTextClick(it)
        }
        //右边图标点击事件
        find<ImageView>(R.id.iv_left_icon).setOnClickListener {
            onClickTitleBarViewListener?.onRightIconClick(it)
        }
    }

    /**
     * 隐藏内容
     */
    fun hide() {
        find<LinearLayout>(R.id.ll_toolbar).visibility = View.GONE
    }

    /**
     * 获取标题view
     */
    fun getLeftIconView() = find<ImageView>(R.id.iv_left_icon)
    /**
     * 获取标题view
     */
    fun getTitleView() = find<TextView>(R.id.tv_center_title)
    /**
     * 获取标题view
     */
    fun getRightIconView() = find<ImageView>(R.id.iv_right_icon)

    /**
     * 设置标题栏中用于处理内容与状态栏重叠的一个间距
     */
    fun setVToolBarHeight(height: Int) {
        //处理沉浸式标题栏
        find<View>(R.id.v_status_bar).apply {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height)
            layoutParams = params
        }
    }




}