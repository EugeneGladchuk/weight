package com.example.weightofring.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weightofring.R
import com.example.weightofring.databinding.ActivityMainBinding
import com.example.weightofring.ui.fragments.start_fragment.StartFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, StartFragment.newInstance())
                .commit()
        }
    }
}