package com.vitekkor.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity_Cycle", "onCreate invoked")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Activity_Cycle", "onStart invoked")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity_Cycle", "onResume invoked")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity_Cycle", "onPause invoked")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity_Cycle", "onStop invoked")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity_Cycle", "onRestart invoked")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity_Cycle", "onDestroy invoked")
    }
}