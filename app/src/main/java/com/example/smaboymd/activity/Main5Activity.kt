package com.example.smaboymd.activity

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import com.example.smaboymd.custom.LineProgressView
import com.example.smaboymd.service.JsService
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * 和h5交互的操作类
 *
 * [参考博客](https://blog.csdn.net/carson_ho/article/details/64904691)
 *
 *  //加载assets文件夹下的test.html页面
 *
 *  mWebView.loadUrl("file:///android_asset/test.html");
 *
 *  //加载网页
 *
 *  mWebView.loadUrl("http://www.baidu.com");*
 *
 *  注意事项：如何避免WebView内存泄露？
 *
 *  1.不在xml中定义 Webview ，而是在需要的时候在Activity中创建，并且Context使用 getApplicationgContext()
 *
 *  2.在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空。
 *
 */
class Main5Activity : BaseActivity() {


    private var url: String = ""
    private var mWebView: WebView? = null


    private val mWebViewClient = object : WebViewClient() {

        /**
         * 打开网页时不调用系统浏览器， 而是在本WebView中显示；在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
         */
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            //ERR_UNKNOWN_URL_SCHEME处理
            val url = request?.url.toString()
            val hitTestResult = view?.hitTestResult
            return try {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view?.loadUrl(url)
                    true
                } else {
                    var intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    if (packageManager.resolveActivity(intent, 0) == null) {  // 如果手机还没安装app，则跳转到应用市场
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + intent.getPackage())) // 注释2
                    }
                    startActivity(intent)

//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                    startActivity(intent)
                    true
                }
            } catch (e: Exception) {
                false
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            //页面加载结束，关闭加载条
//            find<LineProgressView>(R.id.lpv_progress).clear()

            Log.e("tag", "onPageFinished")
        }


    }

    private val mWebChromeClient = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {

            //更新进度
            find<LineProgressView>(R.id.lpv_progress).updateProgress(newProgress)
            Log.e("tag", newProgress.toString())
        }

    }


    override fun getLayout() = R.layout.activity_main5

    override fun init() {
//        url = "file:///android_asset/index.html"
        url = intent.getStringExtra("url") ?: ""
        if (url.isBlank()) {
            find<TextView>(R.id.tv_web_view).text = "url不能为空哦"
        } else {
            //初始化WebView（这里的context，放置内存泄露推荐用applicationcontext，但会造成h5的alert弹窗无法弹出，使用本页面的context可以弹出）
            mWebView = WebView(this).apply {
                //设置布局属性
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )

                //设置可调用js方法
                settings.javaScriptEnabled = true

                //设置js能直接打开窗口，如window.open
                settings.javaScriptCanOpenWindowsAutomatically = true

                //是否可以缩放，默认true
                settings.setSupportZoom(true)

                //是否显示缩放按钮，默认false
                settings.builtInZoomControls = true

                //设置此属性，可任意比例缩放。大视图模式
                settings.useWideViewPort = true

                //和setUseWideViewPort(true)一起解决网页自适应问题
                settings.loadWithOverviewMode = true

                //是否使用缓存
                settings.setAppCacheEnabled(true)

                //DOM Storage 重点是设置这个
                settings.domStorageEnabled = true

                //设置可弹出h5的弹窗
                webChromeClient = mWebChromeClient

                //设置client(当h5跳转到h5还需要在，当前页面打开时，还需要设置客户端，重写载入方法 )
                webViewClient = mWebViewClient

                //打开js接口
                addJavascriptInterface(JsService, "android")

                //android webview组件包含3个隐藏的系统接口：searchBoxJavaBridge_, accessibilityTraversal以及accessibility，恶意程序可以利用它们实现远程代码执行
                removeJavascriptInterface("searchBoxJavaBridge_")
                removeJavascriptInterface("accessibility")
                removeJavascriptInterface("accessibilityTraversal")

                //可以后退
                canGoBack()

                //可以前进
                canGoForward()

            }

            //将WebView放入容器
            find<FrameLayout>(R.id.fl_web_view).addView(mWebView)

            //设置载入url（该方法不能直接在WebView初始化时调用，不然使用url时，加载不出来）
//            mWebView?.loadUrl("https://www.baidu.com")
            mWebView?.loadUrl(url)
        }

        //初始话标题
        initTitleBar(find(R.id.tbv_title), String.format("%s", "Main5Activity"))

        //进入测试
        find<Button>(R.id.btn00).setOnClickListener {
            url = "file:///android_asset/index.html"
            mWebView?.loadUrl(url)
        }
        //有参有返回
        find<Button>(R.id.btn01).setOnClickListener {
            mWebView?.evaluateJavascript(
                "sum(1,2)"
            ) {
                toast(it)
            }

        }
        //有参无返回
        find<Button>(R.id.btn02).setOnClickListener {
            val content = "hello world"
//            mWebView.loadUrl("JavaScript:alertMessage('hello word')")
            mWebView?.loadUrl("JavaScript:alertMessage(\"$content\")")

        }
        //无参无返回
        find<Button>(R.id.btn03).setOnClickListener {
            mWebView?.loadUrl("JavaScript:show()")

        }
        //无参有返回
        find<Button>(R.id.btn04).setOnClickListener {
            mWebView?.evaluateJavascript(
                "getMsg()"
            ) {
                toast(it)
            }

        }


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView?.canGoBack() == true) {
            mWebView?.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        //销毁WebView
        if (mWebView != null) {
            mWebView?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            mWebView?.clearHistory()
            (mWebView?.parent as ViewGroup).removeView(mWebView)
            mWebView?.destroy()
            mWebView = null
        }
        super.onDestroy()
    }
}
