package com.example.smaboymd.activity

import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import com.example.smaboymd.service.JsService
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

    val mWebViewClient = object : WebViewClient() {

        /**
         * 打开网页时不调用系统浏览器， 而是在本WebView中显示；在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
         */
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }

    val mWebChromeClient = object : WebChromeClient() {

    }


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
            webChromeClient = mWebChromeClient

            //设置client(当h5跳转到h5还需要在，当前页面打开时，还需要设置客户端，重写载入方法 )
            webViewClient = mWebViewClient

            //打开js接口
            addJavascriptInterface(JsService,"android")

            //可以后退
            canGoBack()

            //可以前进
            canGoForward()
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
//            mWebView.loadUrl("JavaScript:alertMessage('hello word')")
            mWebView.loadUrl("JavaScript:alertMessage(\"$content\")")

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event)
    }
}
