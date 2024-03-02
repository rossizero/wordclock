package com.rossi.myclocklite

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import androidx.preference.PreferenceManager

class ClockWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        return WallpaperEngine()
    }

    private inner class WallpaperEngine() : Engine(), OnSharedPreferenceChangeListener {
        private val wordClock = WordClock()
        private var height: Int = 0
        private var width: Int = 0

        private var handler: Handler
        private var runnable: Runnable
        private var preview: Boolean = false

        init {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            prefs.registerOnSharedPreferenceChangeListener(this)
            handler = Handler(Looper.getMainLooper())
            runnable = object: Runnable {
                override fun run() {
                    draw()
                    handler.postDelayed(this, 500)
                }
            }
            handler.post(runnable)
        }

        override fun onSurfaceChanged(
            holder: SurfaceHolder?,
            format: Int,
            width: Int,
            height: Int
        ) {
            super.onSurfaceChanged(holder, format, width, height)
            this.height = height
            this.width = width
        }

        private fun draw() {
            val canvas = surfaceHolder?.lockCanvas() ?: return
            wordClock.draw(canvas, applicationContext, width, height)
            surfaceHolder.unlockCanvasAndPost(canvas)
        }

        private fun config() {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@ClockWallpaperService)
            val backgroundColor = prefs.getInt("BACKGROUND_COLOR", 0xff0000)
            val fontColorHighlighted = prefs.getInt("FONT_COLOR_HIGHLIGHTED", 0x00ff00)
            val fontColorDefault = prefs.getInt("FONT_COLOR_DEFAULT", 0x0000ff)
            val showWorld = prefs.getBoolean("SHOW_WORLD", false)
            val showEggs = prefs.getBoolean("SHOW_EGGS", false)
            val usePreset = prefs.getBoolean("USE_PRESETS", false)
            val presetIndex = prefs.getString("PRESET_LIST", "1")
            val fontIndex = prefs.getString("FONT_LIST", "1")
            val marginX = prefs.getInt("MARGIN_X", 100)
            val marginY = prefs.getInt("MARGIN_Y", 100)
            val iconSize = prefs.getInt("ICON_SIZE", 100)

            if (fontIndex != null && presetIndex != null) {
                wordClock.config(this@ClockWallpaperService,
                    Integer.parseInt(fontIndex),
                    backgroundColor,
                    fontColorDefault,
                    fontColorHighlighted,
                    marginX,
                    marginY,
                    usePreset,
                    Integer.parseInt(presetIndex),
                    showEggs,
                    showWorld,
                    iconSize.toFloat(),
                    preview)
                draw()
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@ClockWallpaperService)
            prefs.unregisterOnSharedPreferenceChangeListener(this)
            handler.removeCallbacks(runnable)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?,key: String?) {
            config()
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder?) {
            super.onSurfaceDestroyed(holder)
        }

        override fun onSurfaceRedrawNeeded(holder: SurfaceHolder?) {
            super.onSurfaceRedrawNeeded(holder)
        }

        override fun onSurfaceCreated(holder: SurfaceHolder?) {
            super.onSurfaceCreated(holder)
            this.preview = isPreview
            config()
        }
    }

}