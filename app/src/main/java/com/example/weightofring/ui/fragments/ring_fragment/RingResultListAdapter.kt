package com.example.weightofring

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weightofring.data.database.ringresult.RingResult
import com.example.weightofring.databinding.ItemRingResultBinding



class RingResultAdapter(val clickListener: (item: RingResult) -> Unit) :
    RecyclerView.Adapter<RingResultAdapter.RingResultViewHolder>() {

    private val ringResultList: MutableList<RingResult> = mutableListOf()

    fun updateData(newList: List<RingResult>) {
        ringResultList.clear()
        ringResultList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingResultViewHolder {
        val binding = (ItemRingResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        val viewHolder = RingResultViewHolder(binding)

        binding.imageButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION){
                ringResultList.getOrNull(position)?.let{
                    clickListener.invoke(it)
                }
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RingResultViewHolder, position: Int) {
        holder.bind(ringResultList[position])
    }

    override fun getItemCount(): Int {
        return ringResultList.size
    }

    class RingResultViewHolder(
        var binding: ItemRingResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ringResult: RingResult) {
            binding.ringWidthResult.text = ringResult.width
            binding.ringSizeResult.text = ringResult.size
            binding.ringThicknessResult.text = ringResult.thickness
            binding.resultInList.text = ringResult.result
            binding.typeMetal.text = ringResult.type
        }
    }
}