package com.vitekkor.task3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vitekkor.task3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: ImageLoader by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.loadImage.setOnClickListener {
            viewModel.loadImage()
        }
        viewModel.getBitmap().observe(this, { bitmap -> binding.image.setImageBitmap(bitmap) })
        setContentView(binding.root)
    }
}