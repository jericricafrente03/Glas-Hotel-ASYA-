package com.ph.bittelasia.meshtv.tv.glasshoteldemo.ui.airmedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ph.bittelasia.libvlc.control.annotation.PlayerActivityLayout
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.controller.airmedia.AirmediaAdapter
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.controller.amenities.AmenitiesAdapter
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.core.BaseFragment
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.data.ProjectData
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentAirmediaBinding
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentAmenitiesBinding
import kotlinx.coroutines.launch

@PlayerActivityLayout(R.layout.fragment_airmedia)
class Airmedia : BaseFragment<FragmentAirmediaBinding>() {
    private lateinit var airmediaAdapter : AirmediaAdapter
    override fun addContents() {
        super.addContents()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val data = ProjectData()
                launch {
                    binding.apply {
                        airmediaIV.load(R.drawable.amenitiescover)
                        rvAirmedia.setHasFixedSize(true)
                        rvAirmedia.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        airmediaAdapter = AirmediaAdapter {
                            tvCaption.text = it.caption
                        }
                        airmediaAdapter.submitList(data.getAirmedia())
                        rvAirmedia.adapter = airmediaAdapter

                        launch {
                            tvBack.setOnFocusChangeListener { _, b ->
                                if (b) {
                                    val anim: Animation = AnimationUtils.loadAnimation(context, R.anim.slideup)
                                    tvBack.startAnimation(anim)
                                } else {
                                    tvBack.clearAnimation()
                                }
                            }
                            tvBack.setOnClickListener {
                                activity?.onBackPressed()
                            }
                        }
                    }
                }
            }
        }
    }
    override fun getLayout() = R.layout.fragment_airmedia
}