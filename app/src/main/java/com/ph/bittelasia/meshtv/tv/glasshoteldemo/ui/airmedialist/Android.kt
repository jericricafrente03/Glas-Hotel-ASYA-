package com.ph.bittelasia.meshtv.tv.glasshoteldemo.ui.airmedialist

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
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.controller.airmedia.AirmediaAdapter
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.core.BaseFragment
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.data.ProjectData
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentAirmediaBinding
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentAndroidBinding
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.utils.ADB
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.utils.AppManager
import kotlinx.coroutines.launch

class Android : BaseFragment<FragmentAndroidBinding>() {
    override fun addContents() {
        super.addContents()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    binding.apply {

                        airmediaIV.load(R.drawable.amenitiescover)
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
                            btnAndroid.setOnClickListener {
                                AppManager.isMiracastOpen = true
                                ADB.exec( "monkey -p com.droidlogic.miracast -c android.intent.category.LAUNCHER 1")
                            }
                        }
                    }
                }
            }
        }
    }
    override fun getLayout() = R.layout.fragment_android
}