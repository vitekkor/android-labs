package com.vitekkor.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vitekkor.task5.databinding.Activity3Binding

class Activity3 : Fragment() {
    private lateinit var binding: Activity3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Activity3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toFirst.setOnClickListener {
            findNavController().navigate(R.id.Activity1)
        }
        binding.toSecond.setOnClickListener {
            findNavController().navigate(R.id.Activity2)
        }
    }
}