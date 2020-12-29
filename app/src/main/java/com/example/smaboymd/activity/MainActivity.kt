package com.example.smaboymd.activity

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import com.example.smaboymd.custom.TitleBarView
import kotlinx.coroutines.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private val strArray = arrayOf("模块一","模块二","模块三","模块四","模块五","模块六")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        //动态加载shortcut
        initShortCut()
    }

    private fun initShortCut() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val shortcutManager = getSystemService(ShortcutManager::class.java)
            val shortcut = ShortcutInfo.Builder(this, "id01")
                .setShortLabel("我是短标题")
                .setLongLabel("我是长标题我是长标题")
                .setIcon(Icon.createWithResource(this@MainActivity, R.mipmap.ic_launcher_round))
                .setIntent(Intent(this,Main2Activity::class.java).apply {
                    action = Intent.ACTION_VIEW
                })
                .build()
            val shortcut02 = ShortcutInfo.Builder(this, "id02")
                .setShortLabel("我是短标题02")
                .setLongLabel("我是长标题我是长标题02")
                .setIcon(Icon.createWithResource(this@MainActivity, R.mipmap.ic_launcher_round))
                .setIntent(Intent(this,Main3Activity::class.java).apply {
                    action = Intent.ACTION_VIEW
                })
                .build()
            val shortcut03 = ShortcutInfo.Builder(this, "id03")
                .setShortLabel("我是短标题03")
                .setLongLabel("我是长标题我是长标题03")
                .setIcon(Icon.createWithResource(this@MainActivity, R.mipmap.ic_launcher_round))
                .setIntent(Intent(this,Main4Activity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra("isLogin",true)

                })
                .build()
            shortcutManager?.dynamicShortcuts = mutableListOf(shortcut,shortcut02,shortcut03)
        } else {
            return
        }
    }



    override fun getLayout(): Int {
        return R.layout.activity_main
    }

     override fun init() {
        initTitleBar(find(R.id.tbv_title),String.format("%s","首页"))


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
            startActivity<Main4Activity>("isLogin" to true)
//            toast(strArray[2])
        }
        find<Button>(R.id.btn_04).apply {
            text = strArray[3]
        }.setOnClickListener{
            startActivity<Main5Activity>("url" to "https://www.baidu.com")
//            toast(strArray[3])
        }
        find<Button>(R.id.btn_05).apply {
            text = strArray[4]
        }.setOnClickListener{
            toast(strArray[4])
            startActivity<MainFlutterActivity>()
        }
        find<Button>(R.id.btn_06).apply {
            text = strArray[5]
        }.setOnClickListener{
            toast(strArray[5])
        }

         GlobalScope.launch {

         }

         GlobalScope.async {

         }


         runBlocking {  }



    }


}
