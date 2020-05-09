package com.example.mywatch

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.ImageView
import android.widget.Toast

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()


        findViewById<ImageView>(R.id.iv_icon).setOnClickListener {
            Toast.makeText(baseContext,"hello",Toast.LENGTH_LONG).show()
        }
    }

}
