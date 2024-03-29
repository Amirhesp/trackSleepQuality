package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepBinding

class SleepNightAdapter(val clickListener: SleepNightListener)  : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ListItemSleepBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = getItem(position)!!
        holder.bind(item, clickListener)

    }

    class ViewHolder(val binding: ListItemSleepBinding) : RecyclerView.ViewHolder(binding.root) {


        // this is an example for encapsulation(hide the details) which is a concept of OOP
        fun bind(item: SleepNight, clickListener: SleepNightListener) {
            binding.sleep = item
            binding.executePendingBindings()
            binding.clickListener = clickListener

//            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)

        }
    }


}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}

class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}