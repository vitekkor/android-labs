package com.vitekkor.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.vitekkor.task5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.about.setOnClickListener {
            val controller = binding.fragmentContainerView.findNavController()
            if (controller.currentDestination?.label?.contains("About") != true)
                controller.navigate(R.id.action_Activity_to_ActivityAbout)
        }
    }
}