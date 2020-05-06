package com.example.smaboymd.activity

import android.annotation.SuppressLint
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * 和h5交互的操作类
 *
 *  //加载assets文件夹下的test.html页面
 *  mWebView.loadUrl("file:///android_asset/test.html");*
 *  //加载网页
 *  mWebView.loadUrl("http://www.baidu.com");*
 *
 */
class Main5Activity : BaseActivity() {


    lateinit var mWebView : WebView


    override fun getLayout() = R.layout.activity_main5

    override fun init() {
        //初始话标题
        initTitleBar(find(R.id.tbv_title),String.format("%s","Main5Activity"))

        //初始化WebView的配置
        mWebView = find<WebView>(R.id.wb_web_view).apply {
            loadUrl("file:///android_asset/index.html")
            //设置可调用js方法
            settings.javaScriptEnabled = true

            //设置可弹出h5的弹窗
            webChromeClient = WebChromeClient()
        }

        //按钮监听
        find<Button>(R.id.btn01).setOnClickListener{
//            mWebView.loadUrl("JavaScript:sum('1','2')")
            mWebView.evaluateJavascript("sum(1,2)"
            ) {
                toast(it)
            }

        }
        find<Button>(R.id.btn02).setOnClickListener{
            val content = "hello world"
            mWebView.loadUrl("JavaScript:alertMessage('hello word')")
//            mWebView.loadUrl("JavaScript:alertMessage(\"$content\")")

        }
        find<Button>(R.id.btn03).setOnClickListener{
            mWebView.loadUrl("JavaScript:show()")

        }
        find<Button>(R.id.btn04).setOnClickListener{
            mWebView.evaluateJavascript("getMsg()"
            ) {
                toast(it)
            }

        }


    }
}
