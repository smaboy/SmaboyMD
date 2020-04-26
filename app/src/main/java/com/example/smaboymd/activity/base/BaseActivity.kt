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
        setContentView(R.layout.activity_base)

        //initTitle
        initToolbar()

    }

    /**
     * 初始化标题栏
     */
    private fun initToolbar(){
        //将布局id放入内容布局
        find<FrameLayout>(R.id.fl_content).apply {
            addView(LayoutInflater.from(context).inflate(getLayout(),null,false))
        }

        //标题栏处理
        find<TitleBarView>(R.id.view_title_bar).apply {
            setVToolBarHeight(statusBarHeight)
            getTitleView().text = getString(R.string.app_name)
        }


    }
    /**
     * 获取标题栏控件
     */
    fun getTitleBarView() = find<TitleBarView>(R.id.view_title_bar)



    /**
     * 设置布局id
     */
    @LayoutRes abstract fun getLayout() : Int

}