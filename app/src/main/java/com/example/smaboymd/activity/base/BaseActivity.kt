package com.example.smaboymd.activity.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.example.smaboymd.R
import com.example.smaboymd.custom.TitleBarView
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.fitsStatusBarView
import com.gyf.immersionbar.ktx.immersionBar
import com.gyf.immersionbar.ktx.statusBarHeight
import kotlinx.android.synthetic.main.item_list_content.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

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


}