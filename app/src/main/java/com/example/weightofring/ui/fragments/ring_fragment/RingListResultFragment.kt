package com.example.weightofring.ui.fragments.ring_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weightofring.databinding.FragmentRingListResultBinding

class RingListResultFragment : Fragment() {

    private lateinit var binding: FragmentRingListResultBinding

    private lateinit var recyclerView: RecyclerView

    private val adapter = RingResultListAdapter(
        clickListener = {
            viewModel.deleteButtonClick(it)
        }
    )

    private lateinit var viewModel: CalculateRingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[CalculateRingViewModel::class.java]

        // Inflate the layout for this fragment
        binding = FragmentRingListResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.recyclerViewRingList
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        viewModel.myDataList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

    }


    companion object {

        const val RING_LIST_RESULT = "RingListResultFragment"

        fun newInstance() = RingListResultFragment()

    }
}
