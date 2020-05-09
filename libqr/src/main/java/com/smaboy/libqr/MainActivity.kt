package com.smaboy.libqr

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smaboy.libqr.zbar.CaptureActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    private val REQUEST_CODE_SCAN = 0x0000 // 扫描二维码

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_scan).setOnClickListener {
            //动态权限申请
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
            } else {
                goScan()
            }
        }
    }

    /**
     * 跳转到扫码界面扫码
     */
    private fun goScan() {
        val intent = Intent(this@MainActivity, CaptureActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_SCAN)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            1 ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan()
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
            }

            else -> {
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when(requestCode){
            REQUEST_CODE_SCAN ->{
                if (resultCode == RESULT_OK && data != null){
                    //获取扫描结果
                    val bundle = data.extras
                    val result = bundle?.getString(CaptureActivity.EXTRA_STRING)
                    tv_scanResult.text = "扫描结果：$result"
                }
            }
        }

    }
}
