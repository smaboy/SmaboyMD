package com.example.smaboymd.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smaboymd.R
import com.example.smaboymd.activity.base.BaseActivity
import com.gyf.immersionbar.ktx.immersionBar
import com.gyf.immersionbar.ktx.statusBarHeight
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class Main4Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState)

        //获取状态栏的高度，设置标题栏的偏移量
        val toolbar = find<LinearLayout>(R.id.ll_toolbar)
        val vStatusBar = find<View>(R.id.v_status_bar)
        immersionBar().apply {
            //防止自己的标题栏和状态栏重叠导致，标题显示不清
            //这里将标题栏偏移状态栏的高度，以显示完全
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,statusBarHeight)
            vStatusBar.layoutParams = params
        }

        find<ImageView>(R.id.iv_head).setOnClickListener {
            toast("我是头像啊")
        }

        val option = RequestOptions().apply {
            circleCrop()
        }
        Glide.with(this).load(R.drawable.woman01).apply(option).into(find(R.id.iv_head))




    }

    override fun getLayout(): Int {
        return R.layout.activity_main4
    }
}
