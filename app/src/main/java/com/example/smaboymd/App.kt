package com.example.smaboymd

import android.app.Application
import com.simple.spiderman.SpiderMan

/**
 * 类名: App
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/4/21 7:42 PM
 *
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        //初始化崩溃库
        SpiderMan.init(this)
    }
}