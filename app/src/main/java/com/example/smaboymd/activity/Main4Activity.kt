package com.example.smaboymd.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import com.gyf.immersionbar.ktx.fitsStatusBarView
import com.gyf.immersionbar.ktx.immersionBar
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class Main4Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState)
        //获取状态栏的高度，设置标题栏的偏移量
        immersionBar().apply {
            //防止自己的标题栏和状态栏重叠导致，标题显示不清
            //这里将标题栏偏移状态栏的高度，以显示完全
            fitsStatusBarView(find(R.id.v_status_bar))
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
