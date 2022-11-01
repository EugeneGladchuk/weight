package com.example.weightofring

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.databinding.FragmentCalculateRingBinding


class CalculateRingFragment : Fragment() {

    private lateinit var binding: FragmentCalculateRingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalculateRingBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        const val RING = "CalculateRingFragment"

        @JvmStatic
        fun newInstance() = CalculateRingFragment()
    }
}