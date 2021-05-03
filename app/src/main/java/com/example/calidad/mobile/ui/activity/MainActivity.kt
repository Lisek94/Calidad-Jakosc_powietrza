package com.example.calidad.mobile.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calidad.R
import com.example.calidad.databinding.ActivityMainBinding
import com.example.calidad.mobile.network.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_SplashScreen)
        setContentView(binding.root)
    }
}
