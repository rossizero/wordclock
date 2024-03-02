package com.rossi.myclockfull

import android.graphics.Color
import kotlin.random.Random

class Presets {
    companion object {
        private val presets = arrayOf(
            arrayOf( // 0
                Color.rgb(0, 0, 0),
                Color.rgb(255, 255, 255),
                Color.rgb(70, 70, 70),
            ),
            arrayOf( // 1
                Color.rgb(255, 255, 255),
                Color.rgb(0, 0, 0),
                Color.rgb(220, 220, 220),
            ),
            arrayOf( // 2
                Color.rgb(0, 51, 102),
                Color.rgb(255, 255, 255),
                Color.rgb(0, 51, 102),
            ),
            arrayOf( // 3
                Color.rgb(25, 0, 51),
                Color.rgb(255, 255, 255),
                Color.rgb(25, 0, 51),
            ),
            arrayOf( // 4
                Color.rgb(0, 25, 0),
                Color.rgb(255, 255, 255),
                Color.rgb(0, 25, 0),
            ),
            arrayOf( // 5
                Color.rgb(229, 204, 255),
                Color.rgb(0, 0, 0),
                Color.rgb(229, 204, 255),
            ),
            arrayOf( // 6
                Color.rgb(204, 255, 204),
                Color.rgb(0, 0, 0),
                Color.rgb(204, 255, 204),
            ),
            arrayOf( // 7
                Color.rgb(255, 229, 204),
                Color.rgb(0, 0, 0),
                Color.rgb(255, 229, 204),
            ),
            arrayOf( // 8
                Color.rgb(204, 255, 255),
                Color.rgb(0, 0, 0),
                Color.rgb(204, 255, 255),
            ),
            arrayOf( // 9
                Color.rgb(255, 229, 204),
                Color.rgb(0, 0, 0),
                Color.rgb(200, 200, 200),
            ),
            arrayOf( // 10
                Color.rgb(229, 204, 255),
                Color.rgb(0, 0, 0),
                Color.rgb(200, 200, 200),
            ),
            arrayOf( // 11
                Color.rgb(204, 255, 255),
                Color.rgb(0, 0, 0),
                Color.rgb(200, 200, 200),
            ),
            arrayOf( // 12
                Color.rgb(204, 255, 204),
                Color.rgb(0, 0, 0),
                Color.rgb(200, 200, 200),
            ),
            arrayOf( // 13
                Color.rgb(70, 70, 70),
                Color.rgb(220, 220, 220),
                Color.rgb(130, 130, 130),
            ),
            arrayOf( // 13
                Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
            )
        )

        fun get(presetIndex: Int): Array<Int> {
            return presets[presetIndex]
        }

    }
}