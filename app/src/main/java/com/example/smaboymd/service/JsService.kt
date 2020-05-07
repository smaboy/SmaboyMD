package com.example.smaboymd.service

import android.webkit.JavascriptInterface

/**
 * 类名: JsService
 * 类作用描述: 为h5提供方法的服务类
 * 作者: liyongliang3
 * 创建时间: 2020/5/7 10:31 AM
 *
 */
object JsService {

    @JavascriptInterface
    @JvmStatic
    fun back(): String{
        return "我是来自kotlin的返回值"
    }
}