package com.example.smaboymd.activity.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.smaboymd.R
import com.gyf.immersionbar.ImmersionBar

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
        ImmersionBar.with(this)
            .statusBarColor(R.color.colorPrimary)
            .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
            .init()
    }
}