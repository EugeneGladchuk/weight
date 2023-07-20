package com.example.weightofring.ui.fragments.amount_gem_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.R
import com.example.weightofring.databinding.FragmentCalculateAmountGemBinding
import com.example.weightofring.databinding.FragmentCalculateCrossBinding
import com.example.weightofring.ui.fragments.cross_fragment.CalculateCrossFragment

class CalculateAmountGemFragment : Fragment() {

    lateinit var binding: FragmentCalculateAmountGemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalculateAmountGemBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val AMOUNT_GEM = "CalculateCrossFragment"

        fun newInstance() = CalculateAmountGemFragment()
    }
}