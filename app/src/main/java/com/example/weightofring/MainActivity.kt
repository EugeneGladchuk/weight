package com.example.weightofring

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.weightofring.databinding.ActivityMainBinding


class MainActivity : Activity() {

    //TODO ViewModel without DI или without dagger
    //MVVM without dagger

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButtonRing.setOnClickListener {
            val intent = Intent(this,Calculate::class.java)
            startActivity(intent)
        }

        binding.imageButtonGem.setOnClickListener {
            val intent = Intent(this,CalculateCarat::class.java)
            startActivity(intent)
        }

    }
}