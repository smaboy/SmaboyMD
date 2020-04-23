package com.example.smaboymd.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.example.smaboymd.R
import com.example.smaboymd.activity.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_main4.*
import org.jetbrains.anko.find

class Main4Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

    }
}
