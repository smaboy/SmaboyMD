package com.example.smaboymd

import android.app.Application
import com.simple.spiderman.SpiderMan
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * 类名: App
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/4/21 7:42 PM
 *
 */
class App : Application() {

    lateinit var flutterEngine: FlutterEngine
    override fun onCreate() {
        super.onCreate()

        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache
            .getInstance()
            .put("engine_id", flutterEngine)

        //初始化崩溃库
        SpiderMan.init(this)
    }
}