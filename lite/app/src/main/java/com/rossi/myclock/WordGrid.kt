package com.rossi.myclock

class WordGrid {
    private var grid: Array<Array<TextPart>> = createGrid()

    inner class TextPart(private var text: String, private var minutes: IntArray = intArrayOf(), private var hours: IntArray = intArrayOf(), private var alwaysHighlighted: Boolean = false) {
        private var highlighted: Boolean = false

        fun highlighted(): Boolean {
            return highlighted
        }

        fun text(): String {
            return text
        }

        fun update(minute: Int, hour: Int) {
            highlighted = minute in this.minutes || hour in this.hours || alwaysHighlighted
        }
    }

    fun updateTime(minute: Int, hour: Int) {
        val to = (34..59).toList()

        var hoursReal = hour
        if (minute in to) {
            hoursReal += 1
        }
        hoursReal %= 24

        for (row in grid) {
            for (part in row) {
                part.update(minute, hoursReal)
            }
        }
    }

    fun grid(): Array<Array<TextPart>> {
        return grid
    }

    private fun createGrid(): Array<Array<TextPart>> {
        val five = (4..7).toList() + (53..56).toList()
        val ten = (8..13).toList() + (48..53).toList()
        val quarter = (14..17).toList() + (43..47).toList()
        val twenty = (18..25).toList() + (34..42).toList()
        val half = (26..33).toList()

        return arrayOf(
            arrayOf(
                TextPart("it", alwaysHighlighted=true),
                TextPart("h"),
                TextPart("is", alwaysHighlighted=true),
                TextPart("av"),
                TextPart("aegr")
            ),
            arrayOf(
                TextPart("ea"),
                TextPart("quarter", minutes=quarter.toIntArray()),
                TextPart("td")
            ),
            arrayOf(
                TextPart("twenty", minutes=twenty.toIntArray()),
                TextPart("five", minutes=five.toIntArray()),
                TextPart("a"),
            ),
            arrayOf(
                TextPart("half", minutes=half.toIntArray()),
                TextPart("y"),
                TextPart("ten", minutes=ten.toIntArray()),
                TextPart("b"),
                TextPart("to", minutes=(34..56).toList().toIntArray())
            ),
            arrayOf(
                TextPart("past", minutes=(4..33).toList().toIntArray()),
                TextPart("eni"),
                TextPart("nine", hours=intArrayOf(9, 21)),
            ),
            arrayOf(
                TextPart("one", hours=intArrayOf(1, 13)),
                TextPart("six", hours=intArrayOf(6, 18)),
                TextPart("three", hours=intArrayOf(3, 15)),
            ),
            arrayOf(
                TextPart("four", hours=intArrayOf(4, 16)),
                TextPart("five", hours=intArrayOf(5, 17)),
                TextPart("two", hours=intArrayOf(2, 14))
            ),
            arrayOf(
                TextPart("eight", hours=intArrayOf(8, 20)),
                TextPart("eleven", hours=intArrayOf(11, 23))
            ),
            arrayOf(
                TextPart("seven", hours=intArrayOf(7, 19)),
                TextPart("twelve", hours=intArrayOf(12, 24))
            ),
            arrayOf(
                TextPart("ten", hours=intArrayOf(10, 22)),
                TextPart("ce"),
                TextPart("oclock", minutes=intArrayOf(57, 58, 59, 0, 1, 2, 3))
            )
        )
    }
}