package com.vitekkor.lab5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vitekkor.lab5.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var task: Future<*>

    private val scope = CoroutineScope(Dispatchers.Default)
    private val job = scope.launch(start = CoroutineStart.LAZY) { countInCoroutine() }

    private lateinit var backgroundThread: Thread

    @Volatile
    var secondsElapsed: Int = 0
    var startTime: Long = 0
    private lateinit var textSecondsElapsed: TextView

    private val sharedPref: SharedPreferences
        get() = this.getPreferences(Context.MODE_PRIVATE)
    private var mode = 0

    companion object {
        const val SECONDS = "SECONDS"
        const val MODE = "MODE"
    }

    private fun getElapsedSeconds(): Long =
        secondsElapsed + (System.currentTimeMillis() - startTime) / 1000

    private suspend fun countInCoroutine() {
        while (true) {
            delay(10)
            Log.d("BKGND", "Running. Mode $mode")
            textSecondsElapsed.post {
                textSecondsElapsed.text =
                    getString(R.string.secondsElapsed, getElapsedSeconds())
            }
        }
    }

    private fun count() {
        try {
            while (true) {
                Log.d("BKGND", "Running. Mode $mode")
                sleep(10)
                textSecondsElapsed.post {
                    textSecondsElapsed.text =
                        getString(R.string.secondsElapsed, getElapsedSeconds())
                }
            }
        } catch (e: InterruptedException) {
            Log.d("BKGND", "Interrupted. Mode $mode")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.withExecutorService.setOnClickListener {
            when (mode) {
                0 -> backgroundThread.interrupt()
                2 -> job.cancel()
                else -> return@setOnClickListener
            }
            task = executorService.submit { count() }
            mode = 1
            binding.textCountWith.text = getString(R.string.count_with_, "ExecutorService")
        }

        binding.withCoroutines.setOnClickListener {
            when (mode) {
                0 -> backgroundThread.interrupt()
                1 -> Unit
                else -> return@setOnClickListener
            }
            job.start()
            mode = 2
            binding.textCountWith.text = getString(R.string.count_with_, "Coroutines")
        }
        binding.withThreads.setOnClickListener {
            when (mode) {
                1 -> task.cancel(true)
                2 -> job.cancel()
                else -> return@setOnClickListener
            }
            backgroundThread = Thread { count() }
            backgroundThread.start()
            mode = 0
            binding.textCountWith.text = getString(R.string.count_with_, "Threads")
        }

        setContentView(binding.root)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        mode = sharedPref.getInt(MODE, 0)
        textSecondsElapsed.text = getString(R.string.secondsElapsed, secondsElapsed)
        when (mode) {
            0 -> binding.textCountWith.text = getString(R.string.count_with_, "Threads")
            1 -> binding.textCountWith.text = getString(R.string.count_with_, "ExecutorService")
            2 -> binding.textCountWith.text = getString(R.string.count_with_, "Coroutines")
        }
    }

    override fun onStart() {
        startTime = System.currentTimeMillis()
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        when (mode) {
            0 -> {
                backgroundThread = Thread { count() }
                backgroundThread.start()
            }
            1 -> task = executorService.submit { count() }
            else -> job.start()
        }
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        when (mode) {
            0 -> backgroundThread.interrupt()
            1 -> task.cancel(true)
            else -> job.cancel()
        }
        with(sharedPref.edit()) {
            putInt(SECONDS, getElapsedSeconds().toInt())
            putInt(MODE, mode)
            apply()
        }
    }
}
