package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timer.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var currentProgress: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slider = binding.slider
        var textCount = binding.textCount
        val button = binding.buttonStart
        val circleProgress = binding.progressBarCircular

        var updateProgress = {
            circleProgress.progress = currentProgress
            textCount.text = currentProgress.toString()
        }

        slider.addOnChangeListener { _, _, _ ->
            circleProgress.max = slider.value.toInt()
            currentProgress = slider.value.toInt()
            updateProgress()
        }

        button.setOnClickListener {
            updateProgress()
        }
    }
}
