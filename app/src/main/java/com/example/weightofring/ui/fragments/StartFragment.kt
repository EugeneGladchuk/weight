package com.example.weightofring.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.R
import com.example.weightofring.ui.fragments.CalculateGemFragment.Companion.GEM
import com.example.weightofring.ui.fragments.CalculateRingFragment.Companion.RING
import com.example.weightofring.ui.fragments.GoldPriceFragment.Companion.PRICE
import com.example.weightofring.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonRing.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CalculateRingFragment.newInstance())
                    .addToBackStack(RING)
                    .commit()
            }
        }
        binding.imageButtonGem.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CalculateGemFragment.newInstance())
                    .addToBackStack(GEM)
                    .commit()
            }
        }
        binding.imageButtonGoldPrice.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, GoldPriceFragment.newInstance())
                    .addToBackStack(PRICE)
                    .commit()
            }
        }
    }

    companion object {

        fun newInstance() = StartFragment()
    }
}

