package com.ph.bittelasia.meshtv.tv.glasshoteldemo.controller.airmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.item.AirmediaItem
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.AirmediaLayoutBinding

class AirmediaAdapter(val cb: (AirmediaItem) -> Unit) :
    ListAdapter<AirmediaItem, AirmediaAdapter.AirmediaVH>(AmenitiesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirmediaVH {
        val binding = AirmediaLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirmediaVH(binding, cb)
    }

    override fun onBindViewHolder(holder: AirmediaVH, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class AirmediaVH(val binding: AirmediaLayoutBinding, val cb: (AirmediaItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(airmedia: AirmediaItem) {
            binding.apply {
                root.setOnClickListener {
                    cb.invoke(airmedia)
                }
                amenitiesParent.setOnClickListener {
                    when(airmedia.id){
                        1 -> Navigation.findNavController(it).navigate(R.id.action_airmedia_to_android)
                        2 -> Navigation.findNavController(it).navigate(R.id.action_airmedia_to_IOS)
                        3 -> Navigation.findNavController(it).navigate(R.id.action_airmedia_to_windows)
                    }
                }
                binding.root.setOnFocusChangeListener { _, b ->
                    if (b) {
                        cb.invoke(airmedia)
                        val anim: Animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_in_tv)
                        root.startAnimation(anim)
                        anim.fillAfter = true
                    } else {
                        val anim =
                            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_out_tv)
                        root.startAnimation(anim)
                        anim.fillAfter = true
                    }
                }
            }
            binding.amenities = airmedia
        }
    }

    class AmenitiesComparator : DiffUtil.ItemCallback<AirmediaItem>() {
        override fun areItemsTheSame(oldItem: AirmediaItem, newItem: AirmediaItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AirmediaItem, newItem: AirmediaItem) =
            oldItem == newItem
    }
}