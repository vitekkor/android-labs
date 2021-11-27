package com.vitekkor.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.vitekkor.task5.databinding.Activity1Binding

class Activity1 : Fragment() {
    private lateinit var binding: Activity1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Activity1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUI.setupActionBarWithNavController(activity as AppCompatActivity, findNavController())
        binding.bnToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_Activity1_to_Activity2)
        }
    }
}