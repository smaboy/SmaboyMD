package com.example.smaboymd.custom

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.smaboymd.R

/**
 * 类名: SeatTableView
 * 类作用描述: 电影选座view
 * 作者: liyongliang3
 * 创建时间: 2020/4/28 2:39 PM
 *
 * 涉及到的知识点：
 *
 * 1、矩阵Matrix使用，通过Matrix来进行移动、缩放
 *
 * 2、弹性移动、弹性缩放。
 *
 * 3、手势监听的使用通过GestureDetector、ScaleGestureDetector来获得缩放比例幅度。
 *
 */
class SeatTableView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    /**
     * 座位行数
     */
    val row = 6

    /**
     * 座位列数
     */
    val column = 10

    /**
     * 座位横向间距
     */
    val rowSpaces = 10

    /**
     * 座位纵向间距
     */
    val columnSpaces = 20

    /**
     * 画笔
     */
    val paint = Paint()


    var seatBitMap = BitmapFactory.decodeResource(resources,R.mipmap.seat_green)

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        //绘制座位图
        drawSeat(canvas)
    }

    /**
     *  绘制座位图
     *
     */
    private fun drawSeat(canvas: Canvas?) {
        for (i in 0 until row){
            for (j in 0 until column){
                //确定座位的左上顶点坐标
                val left = seatBitMap.width * j + columnSpaces * j
                val top = seatBitMap.height * i + rowSpaces * i
                canvas?.drawBitmap(seatBitMap,left.toFloat(),top.toFloat(),paint)

            }
        }

    }
}