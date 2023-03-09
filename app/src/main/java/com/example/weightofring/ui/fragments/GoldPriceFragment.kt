package com.example.weightofring.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weightofring.ui.viewModels.GoldPriceViewModel
import com.example.weightofring.databinding.FragmentGoldPriceBinding


class GoldPriceFragment : Fragment() {

    lateinit var viewModel: GoldPriceViewModel

    private lateinit var binding: FragmentGoldPriceBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGoldPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity())[GoldPriceViewModel::class.java]

    }

    companion object {

        const val PRICE = "GoldPriceFragment"

        fun newInstance() = GoldPriceFragment()
    }
}