package com.example.weightofring

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.example.weightofring.databinding.ActivityMainBinding


class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonRing.setOnClickListener {
            val intent = Intent(this,Calculate::class.java)
            startActivity(intent)
        }

    }
}