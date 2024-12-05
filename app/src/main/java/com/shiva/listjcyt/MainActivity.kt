package com.shiva.listjcyt
import Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.shiva.listjcyt.ui.theme.ListJcYtTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make the app fullscreen and set the status bar color to black
        window?.let {
            val controller = WindowInsetsControllerCompat(it, it.decorView)
            controller.isAppearanceLightStatusBars = false // Dark icons, false will make it black
            controller.hide(WindowInsetsCompat.Type.systemBars()) // Hide status and navigation bars
        }
        setContent {
            ListJcYtTheme {
                Scaffold {
                    Home(it)
                }
            }

        }
    }
}
