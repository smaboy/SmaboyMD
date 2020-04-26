package com.example.smaboymd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.smaboymd.R
import com.example.smaboymd.activity.base.BaseActivity
import com.example.smaboymd.custom.TitleBarView
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    val strArray = arrayOf("TextInputLayout","DrawerNavigation","Detail Flow","NestedScroll","btn_05","btn_06")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun init() {
        //初始化标题栏
        getTitleBarView().apply {
            getTitleView().text = String.format("%s"," 我是首页")
        }.onClickTitleBarViewListener = object : TitleBarView.OnClickTitleBarViewListener{
            override fun onLeftIconClick(view: View) {
                finish()
            }

            override fun onCenterTextClick(view: View) {
            }

            override fun onRightIconClick(view: View) {
            }
        }

        find<Button>(R.id.btn_01).apply {
            text = strArray[0]
        }.setOnClickListener{
            startActivity<Main2Activity>()
        }
        find<Button>(R.id.btn_02).apply {
            text = strArray[1]
        }.setOnClickListener{
            startActivity<Main3Activity>()
//            toast(strArray[1])
        }
        find<Button>(R.id.btn_03).apply {
            text = strArray[2]
        }.setOnClickListener{
            startActivity<ItemListActivity>()
//            toast(strArray[2])
        }
        find<Button>(R.id.btn_04).apply {
            text = strArray[3]
        }.setOnClickListener{
            startActivity<Main4Activity>()
//            toast(strArray[3])
        }
        find<Button>(R.id.btn_05).apply {
            text = strArray[4]
        }.setOnClickListener{
            toast(strArray[4])
        }
        find<Button>(R.id.btn_06).apply {
            text = strArray[5]
        }.setOnClickListener{
            toast(strArray[5])
        }

    }
}
