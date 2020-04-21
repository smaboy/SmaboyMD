package com.example.smaboymd.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.smaboymd.R
import com.google.android.material.drawable.DrawableUtils
import com.google.android.material.textfield.TextInputLayout
import org.jetbrains.anko.find


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
    }

    private fun init() {

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
