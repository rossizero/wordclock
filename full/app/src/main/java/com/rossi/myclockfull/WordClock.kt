package com.rossi.myclockfull

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.RectF
import android.graphics.Typeface
import com.caverock.androidsvg.SVG
import java.io.InputStream
import java.util.Calendar
import kotlin.math.max


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
        this.currentTypeface = Typeface.createFromAsset(context.assets, getFontFileName(fontIndex))
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

        //icons
        var posX = marginX.toFloat() / 2
        val posY = max(startY - charHeight - iconSize, 0f)

        if(!isPreview) {
            val dayOfYear = WorldDays.getInformation(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1
            )
            if (dayOfYear != null && showWorld) {
                val vector = dayOfYear.first
                val bitmap = getBitmapFromVector(context, vector)
                val paint = Paint().apply {
                    colorFilter = PorterDuffColorFilter(textColorHighlight, PorterDuff.Mode.SRC_IN)
                }
                canvas.drawBitmap(
                    bitmap,
                    null,
                    RectF(posX, posY, iconSize + posX, iconSize + posY),
                    paint
                )
            }

            if (showEggs) {
                if (calendar.get(Calendar.HOUR) == 4 && calendar.get(Calendar.MINUTE) == 20) {
                    val bitmap = getBitmapFromVector(context, R.raw.four_twenty)
                    if (dayOfYear != null) {
                        posX += iconSize + width / 50
                    }
                    val paint = Paint().apply {
                        colorFilter =
                            PorterDuffColorFilter(textColorHighlight, PorterDuff.Mode.SRC_IN)
                    }
                    canvas.drawBitmap(
                        bitmap,
                        null,
                        RectF(posX, posY, iconSize + posX, iconSize + posY),
                        paint
                    )
                }
            }
        } else if(showWorld) {
            val vector = R.raw.wildlife_day
            val bitmap = getBitmapFromVector(context, vector)
            val paint = Paint().apply {
                colorFilter = PorterDuffColorFilter(textColorHighlight, PorterDuff.Mode.SRC_IN)
            }
            canvas.drawBitmap(
                bitmap,
                null,
                RectF(posX, posY, iconSize + posX, iconSize + posY),
                paint
            )
        }
    }

    private fun getBitmapFromVector(context: Context, vector: Int): Bitmap {
        val inputStream: InputStream = context.resources.openRawResource(vector)
        val svg: SVG = SVG.getFromInputStream(inputStream)
        inputStream.close()
        svg.documentHeight = iconSize
        svg.documentWidth = iconSize
        val bitmap = Bitmap.createBitmap(iconSize.toInt(), iconSize.toInt(), Bitmap.Config.ARGB_8888)
        svg.renderToCanvas(Canvas(bitmap))
        return bitmap
    }

    private fun getFontFileName(fontIndex: Int): String {
        when (fontIndex) {
            1 -> return "anonymous_pro.ttf"
            2 -> return "carbon.ttf"
            3 -> return "cella.ttf"
            4 -> return "crystal.TTF"
            5 -> return "gabriele.ttf"
            6 -> return "hack.ttf"
            7 -> return "lekton.ttf"
            8 -> return "luxi.ttf"
            9 -> return "monospace.ttf"
            10 -> return "normafixed.TTF"
            11 -> return "oloron.TTF"
            12 -> return "oslo.ttf"
            13 -> return "secret_code.ttf"
            14 -> return "small_type_writing.ttf"
            15 -> return "source_code_cro.ttf"
            16 -> return "speculo.ttf"
            17 -> return "speculum.ttf"
            else -> return "anonymous_pro.ttf"
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