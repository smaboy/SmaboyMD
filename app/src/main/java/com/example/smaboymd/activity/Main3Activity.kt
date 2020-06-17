package com.example.smaboymd.activity

import android.os.Bundle
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import org.jetbrains.anko.find

class Main3Activity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_main3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTitleBar(find(R.id.tbv_title),String.format("%s","Main3Activity"))
    }

}
