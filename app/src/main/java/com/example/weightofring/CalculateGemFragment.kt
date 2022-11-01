package com.example.weightofring

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.databinding.FragmentCalculateGemBinding


class CalculateGemFragment : Fragment() {

    lateinit var binding: FragmentCalculateGemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculateGemBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        const val GEM = "CalculateGemFragment"

        @JvmStatic
        fun newInstance() = CalculateGemFragment()
    }
}