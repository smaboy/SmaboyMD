package com.example.smaboymd.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * 类名: LineProgressView
 * 类作用描述: java类作用描述
 * 作者: liyongliang3
 * 创建时间: 2020/5/8 10:47 AM
 *
 */
class LineProgressView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * 画笔
     */
    private val paint = Paint()

    /**
     * 矩形
     */
    private var rectF : RectF

    init {
        //初始化画笔配置
        paint.color = Color.RED
        paint.style = Paint.Style.FILL

        //初始化矩形
        rectF = RectF(0f,0f,0.toFloat(),0.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {

        //画矩形
        canvas?.drawRect(rectF,paint)
    }

    /**
     * 更新进度
     */
    fun updateProgress(progress: Int) {
        //当前进度所在的比例
        rectF = if (progress >= 100){
            RectF(0f,0f,0.toFloat(),0.toFloat())
        }else{
            RectF(0f,0f,width*(progress/100f),height.toFloat())
        }
        //刷新view
        invalidate()

        Log.e("tag","updateProgress---width=$width---height=$height")
    }

    /**
     * 去除矩形
     */
    fun clear() {
        rectF =  RectF(0f,0f,0.toFloat(),0.toFloat())
        invalidate()
    }
}