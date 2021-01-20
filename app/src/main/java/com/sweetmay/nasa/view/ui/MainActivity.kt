package com.sweetmay.nasa.view.ui

import android.content.SharedPreferences
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.sweetmay.nasa.App
import com.sweetmay.nasa.R
import com.sweetmay.nasa.view.ui.fragment.BottomNavigationDrawerFragment

class MainActivity : AppCompatActivity() {

    lateinit var sharedPrefs: SharedPreferences
    private var nightMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(App.SETTINGS, MODE_PRIVATE)
        setThemeAccordingToPrefs(checkDarkTheme())
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.bottom_app_bar))

}

    private fun setThemeAccordingToPrefs(nightMode: Boolean) {
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_app_bar, menu)
        val menuItem = menu?.findItem(R.id.theme_change_icon)

        if (nightMode) {
            menuItem?.icon = ContextCompat
                    .getDrawable(this, R.drawable.dark_mode_24px)
        } else {
            menuItem?.icon = ContextCompat
                    .getDrawable(this, R.drawable.dark_mode_filled_24px)

    }
        return true
    }

    private fun checkDarkTheme(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                && !sharedPrefs.contains(App.THEME_KEY)) {
            if (resources.configuration.uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES) {
                sharedPrefs.edit().putBoolean(App.THEME_KEY, true).apply()
                nightMode = true
                nightMode
            } else {
                sharedPrefs.edit().putBoolean(App.THEME_KEY, false).apply()
                nightMode = false
                nightMode
            }
        }else {
            nightMode = sharedPrefs.getBoolean(App.THEME_KEY, false)
            nightMode
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
            R.id.theme_change_icon -> {
                toggleTheme()
                recreate()
            }
        }
        return true
    }

    private fun toggleTheme() {
        if(nightMode){
            sharedPrefs.edit().putBoolean(App.THEME_KEY, false).apply()
        }else {
            sharedPrefs.edit().putBoolean(App.THEME_KEY, true).apply()
        }
    }
}