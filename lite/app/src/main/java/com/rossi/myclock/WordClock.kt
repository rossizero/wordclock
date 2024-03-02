package com.rossi.myclock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import java.util.Calendar


class WordClock {
    private var wordGrid: WordGrid = WordGrid()
    private var calendar: Calendar = Calendar.getInstance()

    //style stuff
    private var currentTypeface: Typeface? = null
    private var backgroundColor: Int = 0
    private var textColor: Int = 0
    private var textColorHighlight: Int = 0
    private var marginX: Int = 100
    private var marginY: Int = 100
    private var usePreset: Boolean = false
    private var presetIndex: Int = 1
    private var showEggs: Boolean = true
    private var showWorld: Boolean = true
    private var iconSize: Float = 200f
    private var isPreview: Boolean = false

    fun config(context: Context,
               fontIndex: Int,
               backgroundColor: Int,
               textColor: Int,
               textColorHighlight: Int,
               marginX: Int,
               marginY: Int,
               usePreset: Boolean,
               presetIndex: Int,
               showEggs: Boolean,
               showWorld: Boolean,
               iconSize: Float,
               isPreview: Boolean){
        this.currentTypeface = Typeface.createFromAsset(context.assets, "secret_code.ttf")
        this.usePreset = usePreset
        this.marginX = marginX
        this.marginY = marginY
        this.presetIndex = presetIndex
        this.showEggs = showEggs
        this.showWorld = showWorld
        this.iconSize = iconSize
        this.isPreview = isPreview

        if (usePreset) {
            val preset = Presets.get(presetIndex)
            this.backgroundColor = preset[0]
            this.textColorHighlight = preset[1]
            this.textColor = preset[2]
        } else {
            this.backgroundColor = backgroundColor
            this.textColor = textColor
            this.textColorHighlight = textColorHighlight
        }
    }

    fun draw(canvas: Canvas, context: Context, width: Int, height: Int) {
        //update time
        wordGrid.updateTime(calendar.get(Calendar.MINUTE), calendar.get(Calendar.HOUR_OF_DAY))

        //background fill
        canvas.drawPaint(Paint().apply {
            color = backgroundColor
            style = Paint.Style.FILL
        })

        //text grid
        val textPaint = calculateFontSizeToFitWidth(width - marginX, Paint().apply {
            style = Paint.Style.FILL
            typeface = currentTypeface
        })

        val charLength = textPaint.measureText("W")
        val charHeight = textPaint.fontMetrics.descent - textPaint.fontMetrics.ascent

        val startX = (width - charLength * 11) / 2
        var x = startX
        val startY = marginY + height.toFloat() / 2 - charHeight * wordGrid.grid().size / 2
        var y = startY

        wordGrid.grid().forEach {
            row ->
            row.forEach {
                part ->
                if (part.highlighted()) {
                    textPaint.color = textColorHighlight
                } else {
                    textPaint.color = textColor
                }
                canvas.drawText(part.text(), x, y, textPaint)
                x += charLength * part.text().length
            }
            x = startX
            y += charHeight
        }
    }

    private fun calculateFontSizeToFitWidth(width: Int, paint: Paint): Paint {
        var low = 0f
        var high = 1000f

        while (low < high - 0.5f) {
            val fontSize = (low + high) / 2
            paint.textSize = fontSize
            val textWidth = paint.measureText("WWWWWWWWWWW")

            if (textWidth < width) {
                low = fontSize
            } else {
                high = fontSize
            }
        }
        return paint
    }
}