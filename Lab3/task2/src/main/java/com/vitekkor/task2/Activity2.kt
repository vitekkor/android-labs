package com.vitekkor.task2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vitekkor.task2.databinding.Activity2Binding


class Activity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Activity2Binding.inflate(layoutInflater)
        binding.bnToFirst.setOnClickListener {
            finish()
        }
        binding.bnToThird.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivityForResult(intent, 0)
        }
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            finish()
        }
    }
}