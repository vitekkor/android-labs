package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView

    companion object {
        const val SECONDS = "SECONDS"
    }

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
    }

    override fun onResume() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        super.onResume()
    }

    override fun onPause() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt(SECONDS, secondsElapsed)
            apply()
        }
        super.onPause()
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
