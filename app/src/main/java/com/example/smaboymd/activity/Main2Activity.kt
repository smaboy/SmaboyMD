package com.example.smaboymd.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.smaboymd.R
import com.example.smaboymd.base.BaseActivity
import com.example.smaboymd.custom.TitleBarView
import com.google.android.material.textfield.TextInputLayout
import com.simple.spiderman.SpiderMan
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


class Main2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            init()
        } catch (e: Exception) {
            SpiderMan.show(e)
        }
    }

    override fun getLayout(): Int {
       return R.layout.activity_main2
    }

    init {

    }
    private fun init() {

        initTitleBar(find(R.id.tbv_title),String.format("%s","Main2Activity"))

        val username = find<TextInputLayout>(R.id.til_username).apply {
            counterMaxLength = 10
            isCounterEnabled = true
        }
        find<EditText>(R.id.et_username).addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.contains("@") == true){
                    username.error = "邮箱格式错误"
                    username.setErrorIconDrawable(R.mipmap.ic_launcher)
                    username.isErrorEnabled = true
                }else {
                    username.isErrorEnabled = false
                }
            }

        })

    }
}
