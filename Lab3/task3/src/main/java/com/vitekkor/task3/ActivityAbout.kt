package com.vitekkor.task3

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.vitekkor.task3.databinding.ActivityAboutBinding

class ActivityAbout : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        binding.text.movementMethod = ScrollingMovementMethod()
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }
}