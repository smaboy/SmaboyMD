package com.example.smaboymd.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.example.smaboymd.custom.TitleBarView
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.view_title_bar.view.*

/**
 * 类名: BaseActivity
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/4/23 5:46 PM
 *
 */
abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化状态栏
       immersionBar{
           statusBarDarkFont(true)//状态栏深色字体
       }
        //设置布局
        setContentView(getLayout())

        //initTitle
        initData()

    }

    /**
     * 初始化页面数据
     */
    protected open fun initData(){}

    /**
     * 设置布局id
     */
    @LayoutRes abstract fun getLayout() : Int

    /**
     * 初始化标题栏
     */
    fun initTitleBar(titleBar: TitleBarView , title : String = "") {
        titleBar.run {
            //标题栏左侧icon处理
            getLeftIconView().apply {

            }
            //标题栏中间内容处理
            getTitleView().apply {
                text = title
            }
            //标题栏左侧icon处理
            getRightIconView().apply {
                visibility = View.GONE

            }
            //标题栏监听默认处理
            onClickTitleBarViewListener = object : TitleBarView.OnClickTitleBarViewListener{
                override fun onLeftIconClick(view: View) {
                    finish()
                }

                override fun onCenterTextClick(view: View) {

                }

                override fun onRightIconClick(view: View) {

                }

            }
        }


    }


}