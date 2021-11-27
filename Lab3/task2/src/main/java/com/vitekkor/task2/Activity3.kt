package com.vitekkor.task2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vitekkor.task2.databinding.Activity3Binding

class Activity3 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Activity3Binding.inflate(layoutInflater)
        binding.bnToFirst.setOnClickListener {
            setResult(1)
            finish()
        }
        binding.bnToSecond.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}