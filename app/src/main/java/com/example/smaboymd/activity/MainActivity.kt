package com.example.smaboymd.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.smaboymd.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    val strArray = arrayOf("btn_01","btn_02","btn_03","btn_04","btn_05","btn_06")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        find<Button>(R.id.btn_01).apply {
            text = strArray[0]
        }.setOnClickListener{
            startActivity<Main2Activity>()
        }
        find<Button>(R.id.btn_02).apply {
            text = strArray[1]
        }.setOnClickListener{
            toast(strArray[1])
        }
        find<Button>(R.id.btn_03).apply {
            text = strArray[2]
        }.setOnClickListener{
            toast(strArray[2])
        }
        find<Button>(R.id.btn_04).apply {
            text = strArray[3]
        }.setOnClickListener{
            toast(strArray[3])
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
