package br.edu.satc.todolistcompose.util

import android.content.Context

class ThemePreference(context: Context) {

    private val prefs = context.getSharedPreferences("theme", Context.MODE_PRIVATE)

    fun save(mode: ThemeMode) {
        prefs.edit().putString("mode", mode.name).apply()
    }

    fun get(): ThemeMode {
        val value = prefs.getString("mode", ThemeMode.LIGHT.name)
        return ThemeMode.valueOf(value!!)
    }
}