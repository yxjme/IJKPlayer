package com.yxjme.ijkplayer.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleDividerDecoration: RecyclerView.ItemDecoration() {

    private var dividerHeight = 0
    private var dividerPaint: Paint? = null

    init {
        dividerPaint = Paint()
        dividerPaint!!.color = Color.RED
        dividerHeight = 2
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
//        outRect.top = dividerHeight
        outRect.bottom = dividerHeight
    }



    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        /*获取列表的中自view数量*/
        val childCount = parent.childCount

        /*获取左右两边的内距*/
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight


        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = view.bottom + dividerHeight.toFloat()
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, dividerPaint!!)
        }
    }



    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }
}