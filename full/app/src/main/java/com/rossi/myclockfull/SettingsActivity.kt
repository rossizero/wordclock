package com.rossi.myclockfull

import android.app.Dialog
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.rarepebble.colorpicker.ColorPreference
import java.util.Calendar


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private var calendar: Calendar = Calendar.getInstance()

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            val dayOfYear = WorldDays.getInformation(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1)
            var description: String? = null

            if (dayOfYear != null) {
                description = dayOfYear.second
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && calendar.get(Calendar.DAY_OF_MONTH) == 13) {
                    description += " and it's Friday the 13th!"
                }
            } else {
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && calendar.get(Calendar.DAY_OF_MONTH) == 13) {
                    description =  "It's Friday the 13th!"
                }
            }
            if (description != null) {
                Toast.makeText(requireContext(), description, Toast.LENGTH_LONG).show()
            }

            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val button = findPreference<Preference>("PREVIEW");
            button?.setOnPreferenceClickListener {
                val intent = Intent()
                intent.action = WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER
                intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT",
                    ComponentName(requireContext(), ClockWallpaperService::class.java))
                startActivity(intent)
                true
            }

            val linkButton = findPreference<Preference>("LINK");
            linkButton?.setOnPreferenceClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/developer?id=cough_cough")
                )
                Log.e("SettingsActivity", "link clicked")
                startActivity(browserIntent)
                true
            }

            val aboutButton = findPreference<Preference>("ABOUT");
            aboutButton?.setOnPreferenceClickListener {
                about()
                true
            }

            val easterEggs = findPreference<Preference>("SHOW_EGGS")
            easterEggs?.setOnPreferenceClickListener {
                val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
                val value: Boolean = prefs.getBoolean("SHOW_EGGS", false)
                if (value) {
                    Toast.makeText(
                        requireContext(),
                        "Eastereggs activated >:D",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val deactivation = "Press this button again! FAST!!! Otherwise an 'Universalgenie' & 'Vogel' is mad at you... And I (this message) swear to you, I will eat a cookie in 0,9356 seconds to show how serious this topic is for me! Now go in the Playstore and give this wonderfull Application about 5+ stars and/or download the full version (would be nice). Just think of it.. It's not much money, but I am no rich person (right now :D) and to continue making those kind of apps (btw games are already in devolpement) your 60 cents are like cookies to me. And the best part: I would like you about 15 degrees celcius more. Okay this sounds like one can buy my friendship but who the f*** cares? See ya in a weil, nä :)\n\nPS: don't take a screenshot of this :P\n\n     o.O"
                    Snackbar.make(requireView(), deactivation, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).setTextMaxLines(25).show()
                }
                true
            }

            //preset switch
            val presetSwitch = findPreference<Preference>("USE_PRESETS")
            presetSwitch?.setOnPreferenceClickListener {
                updatePresetSection()
                true
            }
            updatePresetSection()
        }

        private fun updatePresetSection() {
            val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
            val isSwitchChecked: Boolean = prefs.getBoolean("USE_PRESETS", false)
            val backgroundColorPreference = findPreference<ColorPreference>("BACKGROUND_COLOR")
            val color1Preference = findPreference<ColorPreference>("FONT_COLOR_HIGHLIGHTED")
            val color2Preference = findPreference<ColorPreference>("FONT_COLOR_DEFAULT")
            val presetListPreference = findPreference<ListPreference>("PRESET_LIST")

            if (isSwitchChecked) {
                backgroundColorPreference?.isEnabled = false
                color1Preference?.isEnabled = false
                color2Preference?.isEnabled = false
                presetListPreference?.isEnabled = true
            } else {
                backgroundColorPreference?.isEnabled = true
                color1Preference?.isEnabled = true
                color2Preference?.isEnabled = true
                presetListPreference?.isEnabled = false
            }
        }

        override fun onDisplayPreferenceDialog(preference: Preference) {
            if (preference is ColorPreference) {
                preference.showDialog(this, 0)
            } else {
                super.onDisplayPreferenceDialog(preference)
            }
        }

        private fun about(){
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.about)
            dialog.setTitle("A cough_cough App :)")

            val image = dialog.findViewById<View>(R.id.image) as ImageView
            image.setImageResource(R.drawable.ic_launcher)

            var text = dialog.findViewById<View>(R.id.ColorPickerList) as TextView
            text.text = "\nColor-Picker widget:"
            text = dialog.findViewById<View>(R.id.ambilwarna) as TextView
            text.text = "https://github.com/martin-stone/hsv-alpha-color-picker-android \n HSV-Alpha Color Picker for Android is licensed under the Apache License v2.0."

            text = dialog.findViewById<View>(R.id.SVG) as TextView
            text.text = "SVG Library:"
            text = dialog.findViewById<View>(R.id.SVG_TEXT) as TextView
            text.text = "AndroidSVG is a SVG parser and renderer for Android.\n AndroidSVG is licensed under the Apache License v2.0.\n https://github.com/BigBadaboom/androidsvg \n"

            text = dialog.findViewById<View>(R.id.FontList) as TextView
            text.text = "Fonts:"
            text = dialog.findViewById<View>(R.id.font1) as TextView
            text.text = "1. Anonymous Pro: Copyright (c) 2009 by Mark Simonson (http://www.ms-studio.com, mark@marksimonson.com) (license: http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font2) as TextView
            text.text = "2. Carbon: 1001Fonts Free For Commercial Use License (FFC)(http://www.1001fonts.com/licenses/ffc.html). \n"
            text = dialog.findViewById<View>(R.id.font3) as TextView
            text.text = "3. Cella: 1001Fonts Free For Commercial Use License (FFC) (http://www.1001fonts.com/licenses/ffc.html). \n"
            text = dialog.findViewById<View>(R.id.font4) as TextView
            text.text = "4. Crystal: Version 5.1   25-Mar-99 by Jerry Fitzpatrick (jerryf@braveidea.com). ''You may use the Crystal font family FREE OF CHARGE for any personal or commercial software application or document''. \n"
            text = dialog.findViewById<View>(R.id.font5) as TextView
            text.text = "5.  Gabriele: License: You may use this font software free of charge and at your own risk for both personal and commercial use. \n"
            text = dialog.findViewById<View>(R.id.font6) as TextView
            text.text = "6. Hack: by chrissimpkins: This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font7) as TextView
            text.text = "7. Lekton: This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font8) as TextView
            text.text = "8. Luxi: Luxi fonts copyright (c) 2001 by Bigelow & Holmes Inc. Luxi font instruction code copyright (c) 2001 by URW++ GmbH. All Rights Reserved. Luxi is a registered trademark of Bigelow & Holmes Inc. (info@urwpp.de/design@bigelowandholmes.com). \n"
            text = dialog.findViewById<View>(R.id.font9) as TextView
            text.text = "9.  Monospaced: by George Williams founder of (http://fontforge.github.io/en-US/). This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font10) as TextView
            text.text = "10. Normafixed: by Match Software (http://members.aol.com/MatchSoft). This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font11) as TextView
            text.text = "11.  Oloron: This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font12) as TextView
            text.text = "12. Oslo: 1001Fonts Free For Commercial Use License (FFC)(http://www.1001fonts.com/licenses/ffc.html). \n"
            text = dialog.findViewById<View>(R.id.font13) as TextView
            text.text = "13.  Secret Code: Copyright (c) 1998 by Matthew Welch (http://www.squaregear.net/fonts/license.shtml) \n."
            text = dialog.findViewById<View>(R.id.font14) as TextView
            text.text = "14. SmallTypeWriting: by Manfred Klein. This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font15) as TextView
            text.text = "15.  SourceCodePro: Copyright 2010, 2012 Adobe Systems Incorporated (http://www.adobe.com/), with Reserved Font Name 'Source'. All Rights Reserved. Source is a trademark of Adobe Systems Incorporated in the United States and/or other countries. This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font16) as TextView
            text.text = "16. speculo: by Ocarina. This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            text = dialog.findViewById<View>(R.id.font17) as TextView
            text.text = "17. speculum: by Ocarina. This font (family) is available under the SIL Open Font License (http://scripts.sil.org/OFL). \n"
            dialog.show()
        }
    }
}