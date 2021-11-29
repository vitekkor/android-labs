package com.vitekkor.task4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.vitekkor.task4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var lastImageSignature = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        lastImageSignature = this.getPreferences(MODE_PRIVATE).getLong("signature", 0L)
        if (lastImageSignature != 0L)
            Glide.with(this)
                .load("https://cataas.com/cat/says/android-lectures?width=50?height=50")
                .signature(ObjectKey(lastImageSignature))
                .into(binding.image)
        binding.loadImage.setOnClickListener {
            lastImageSignature = System.currentTimeMillis()
            Glide.with(this)
                .load("https://cataas.com/cat/says/android-lectures?width=50?height=50")
                .signature(ObjectKey(lastImageSignature))
                .into(binding.image)
        }
        setContentView(binding.root)
    }

    override fun onStop() {
        super.onStop()
        this.getPreferences(MODE_PRIVATE).edit().putLong("signature", lastImageSignature).apply()
    }
}