package com.example.weightofring.ui.fragments.calculate_wire_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.R
import com.example.weightofring.databinding.FragmentCalculateAmountGemBinding
import com.example.weightofring.databinding.FragmentCalculateWireBinding
import com.example.weightofring.ui.fragments.amount_gem_fragment.CalculateAmountGemFragment

class CalculateWireFragment : Fragment() {
    lateinit var binding: FragmentCalculateWireBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalculateWireBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val WIRE = "CalculateWireFragment"

        fun newInstance() = CalculateWireFragment()
    }
}