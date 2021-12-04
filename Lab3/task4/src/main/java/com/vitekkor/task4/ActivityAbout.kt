package com.vitekkor.task4

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import com.vitekkor.task4.databinding.ActivityAboutBinding

class ActivityAbout : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        binding.text.movementMethod = ScrollingMovementMethod()
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }
}