package com.gtsl.tflroadstatus.presentation.roadstatus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gtsl.tflroadstatus.R
import com.gtsl.tflroadstatus.databinding.ItemRoadStatusBinding
import com.gtsl.tflroadstatus.presentation.roadstatus.model.UiRoadInfo

class RoadStatusAdapter(
) : RecyclerView.Adapter<RoadStatusAdapter.RoadStatusViewHolder>() {

    private val data = arrayListOf<UiRoadInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoadStatusViewHolder {
        val binding = DataBindingUtil.inflate<ItemRoadStatusBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_road_status,
            parent,
            false
        )
        return RoadStatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoadStatusViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(uiRoadInfoList: List<UiRoadInfo>) {
        data.clear()
        data.addAll(uiRoadInfoList)
        notifyDataSetChanged()
    }

    class RoadStatusViewHolder(
        private val binding: ItemRoadStatusBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uiRoadInfo: UiRoadInfo) {
            binding.roadInfo = uiRoadInfo
            binding.executePendingBindings()
        }
    }
}