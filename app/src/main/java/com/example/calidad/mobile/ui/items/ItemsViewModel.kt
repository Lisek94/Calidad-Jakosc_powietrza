package com.example.calidad.mobile.ui.items

import android.graphics.Color
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    fun setupColor(quality: Int): Int {
        return when (quality) {
            in 0..50 -> Color.GREEN
            in 51..100 -> Color.YELLOW
            in 101..150 -> Color.rgb(255, 165, 0) // Color.ORANGE
            in 151..200 -> Color.RED
            in 201..300 -> Color.MAGENTA
            else -> Color.BLACK
        }
    }
}