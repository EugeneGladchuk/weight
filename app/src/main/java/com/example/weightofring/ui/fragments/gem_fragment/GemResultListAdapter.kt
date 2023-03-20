package com.example.weightofring

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weightofring.data.database.gemresult.GemResult
import com.example.weightofring.databinding.ItemGemResultBinding

class GemResultListAdapter(val clickListener: (item: GemResult) -> Unit) :
    RecyclerView.Adapter<GemResultListAdapter.GemResultViewHolder>() {

    private val gemResultList: MutableList<GemResult> = mutableListOf()

    fun updateData(newList: List<GemResult>) {
        gemResultList.clear()
        gemResultList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GemResultViewHolder {
        val binding =
            (ItemGemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        val viewHolder = GemResultViewHolder(binding)

        binding.imageButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                gemResultList.getOrNull(position)?.let {
                    clickListener.invoke(it)
                }
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: GemResultViewHolder, position: Int) {
        holder.bind(gemResultList[position])
    }

    override fun getItemCount(): Int {
        return gemResultList.size
    }

    class GemResultViewHolder(
        var binding: ItemGemResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gemResult: GemResult) {
            with(binding) {
                gemCutType.text = gemResult.cutType
                gemName.text = gemResult.gemDensity
                gemLengthResult.text = gemResult.gemLength
                gemWidthResult.text = gemResult.gemWidth
                gemDepthResult.text = gemResult.gemDepth
                resultByCarat.text = gemResult.resultCarat
                resultByGram.text = gemResult.resultGram
            }
        }
    }
}