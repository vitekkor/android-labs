package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    @Volatile
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView

    companion object {
        const val SECONDS = "SECONDS"
    }

    private fun getNewBackgroundThread() = Thread {
        try {
            while (true) {
                Thread.sleep(1000)
                Log.d("BKGND", "Running")
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.secondsElapsed, secondsElapsed++)
                }
            }
        } catch (e: InterruptedException) {
            Log.d("BKGND", "Interrupted")
        }
    }

    private lateinit var backgroundThread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        textSecondsElapsed.text = getString(R.string.secondsElapsed, secondsElapsed)
    }

    override fun onStart() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        backgroundThread = getNewBackgroundThread()
        backgroundThread.start()
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(SECONDS, secondsElapsed)
            apply()
        }
        backgroundThread.interrupt()
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(SECONDS)
        super.onRestoreInstanceState(savedInstanceState)
    }*/
}
