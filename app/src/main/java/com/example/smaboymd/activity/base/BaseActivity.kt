package com.example.smaboymd.activity.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.smaboymd.R
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar

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
       immersionBar()
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}