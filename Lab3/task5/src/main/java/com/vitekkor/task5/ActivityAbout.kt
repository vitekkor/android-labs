package com.vitekkor.task5

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.vitekkor.task5.databinding.ActivityAboutBinding

class ActivityAbout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        binding.text.movementMethod = ScrollingMovementMethod()
        setContentView(binding.root)
    }
}