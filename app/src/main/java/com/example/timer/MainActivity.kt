package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.timer.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var currentProgress: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slider = binding.slider
        val textCount = binding.textCount
        val button = binding.buttonStart
        val circleProgress = binding.progressBarCircular

        val updateProgress = {
            circleProgress.progress = currentProgress
            textCount.text = currentProgress.toString()
        }

        slider.addOnChangeListener { _, _, _ ->
            circleProgress.max = slider.value.toInt()
            currentProgress = slider.value.toInt()
            updateProgress()
        }

        button.setOnClickListener {
//            TODO Для реализации таймера понадобится цикл, в котором нужно каждую секунду
//             перерисовывать UI. Не забудьте, что главный поток в Android нельзя блокировать,
//             но в то же время нельзя менять UI из других потоков.  Самым простым способом будет
//             использование корутин с Main диспетчером и метода delay, который не блокирует поток.

            val scope = CoroutineScope(Job() + Dispatchers.Main)
            val job = scope.launch {
                while (currentProgress > 0) {
                    currentProgress-= 1
                    updateProgress()
                    delay(1000)
                }
            }
            button.text = "Stop"
//      TODO Проблема в том, что при создании корутины происходит ее запуск и я не могу управлять job.
//        Запустить, прервать при повторном нажатии на кнопку...
        }
    }
}
