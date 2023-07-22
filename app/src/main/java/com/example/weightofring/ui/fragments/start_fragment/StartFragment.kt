package com.example.weightofring.ui.fragments.start_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weightofring.R
import com.example.weightofring.ui.fragments.gem_fragment.CalculateGemFragment.Companion.GEM
import com.example.weightofring.ui.fragments.ring_fragment.CalculateRingFragment.Companion.RING
import com.example.weightofring.ui.fragments.price_fragment.GoldPriceFragment.Companion.PRICE
import com.example.weightofring.databinding.FragmentStartBinding
import com.example.weightofring.ui.fragments.calculate_wire_fragment.CalculateWireFragment
import com.example.weightofring.ui.fragments.cross_fragment.CalculateCrossFragment
import com.example.weightofring.ui.fragments.gem_fragment.CalculateGemFragment
import com.example.weightofring.ui.fragments.price_fragment.GoldPriceFragment
import com.example.weightofring.ui.fragments.ring_fragment.CalculateRingFragment


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

        binding.buttonRing.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CalculateRingFragment.newInstance())
                    .addToBackStack(RING)
                    .commit()
            }
        }
        binding.buttonGem.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CalculateGemFragment.newInstance())
                    .addToBackStack(GEM)
                    .commit()
            }
        }

        binding.buttonCross.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CalculateCrossFragment.newInstance())
                    .addToBackStack(GEM)
                    .commit()
            }
        }

        binding.buttonWire.setOnClickListener {
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CalculateWireFragment.newInstance())
                    .addToBackStack(GEM)
                    .commit()
            }
        }

        binding.buttonGoldPrice.setOnClickListener {
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

