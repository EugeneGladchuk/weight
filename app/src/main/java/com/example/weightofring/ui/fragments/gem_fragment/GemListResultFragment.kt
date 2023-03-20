package com.example.weightofring.ui.fragments.gem_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weightofring.GemResultListAdapter
import com.example.weightofring.databinding.FragmentGemListResultBinding


class GemListResultFragment : Fragment() {

    lateinit var binding: FragmentGemListResultBinding

    private lateinit var recyclerView: RecyclerView

    private val adapter = GemResultListAdapter(
        clickListener = {
            viewModel.deleteButtonClick(it)
        }
    )

    lateinit var viewModel: CalculateGemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGemListResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[CalculateGemViewModel::class.java]

        recyclerView = binding.recyclerViewRingList
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        viewModel.myDataList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    companion object {

        const val GEM_LIST_RESULT = "GemListResultFragment"

        fun newInstance() = GemListResultFragment()
    }
}
