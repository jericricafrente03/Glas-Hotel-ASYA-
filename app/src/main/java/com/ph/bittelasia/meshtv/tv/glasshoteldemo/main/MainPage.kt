package com.ph.bittelasia.meshtv.tv.glasshoteldemo.main

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.controller.main.MainAdapter
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.core.BaseFragment
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.data.ProjectData
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.db.GlassDB
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.pref.DayMode
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.pref.Mode.readDayMode
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.repository.Repository
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.util.Resource
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentMainPageBinding
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.utils.GlassViewModel
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPage : BaseFragment<FragmentMainPageBinding>() {
    private lateinit var myViewModel: GlassViewModel
    override fun addContents() {
        super.addContents()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val data = ProjectData()
                val repo = Repository(data, GlassDB.db(requireContext()),requireContext())
                val ui = MainAdapter()
                val factoryVm = ViewModelFactory(repo)
                myViewModel = ViewModelProvider(this@MainPage, factoryVm)[GlassViewModel::class.java]
                launch {
                    Glide.with(this@MainPage).asGif().load(R.drawable.n1).into(binding.ivBackground)
                    binding.apply {
                        val typeface = ResourcesCompat.getFont(requireContext(), R.font.selawksl)
                        ivLogo.load(R.drawable.newlogo)
                        tc1.typeface = typeface
                        tc2.typeface = typeface
                        tc3.typeface = typeface
                        uiRv.apply {
                            adapter = ui
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            myViewModel.mainUi.observe(viewLifecycleOwner) {
                                if(it is Resource.Success && it.data!!.isNotEmpty()) {
                                    ui.submitList(it.data)
                                }
                            }
                        }
                    }
                }
                launch {
                    requireContext().readDayMode(Dispatchers.Main) {
                        when(DayMode.ID){
                            "1" -> {
                                Glide.with(this@MainPage)
                                    .asGif()
                                    .load(R.drawable.n4)
                                    .transition(DrawableTransitionOptions.withCrossFade())
                                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                    .listener(object : RequestListener<GifDrawable?> {
                                        override fun onResourceReady(
                                            resource: GifDrawable?,
                                            model: Any?,
                                            target: Target<GifDrawable?>?,
                                            dataSource: DataSource?,
                                            isFirstResource: Boolean
                                        ): Boolean {
                                            resource?.setLoopCount(1)
                                            resource?.registerAnimationCallback(object :
                                                Animatable2Compat.AnimationCallback() {
                                                override fun onAnimationEnd(drawable: Drawable?) {
                                                    Glide.with(this@MainPage).asGif()
                                                        .load(R.drawable.n1)
                                                        .transition(DrawableTransitionOptions.withCrossFade())
                                                        .into(binding.ivBackground)
                                                    binding.owner.setTextColor(Color.parseColor("#FF2E2E2E"))
                                                    binding.tvRoom.setTextColor(Color.parseColor("#FF2E2E2E"))
                                                    binding.tc1.setTextColor(Color.parseColor("#FF2E2E2E"))
                                                    binding.tc2.setTextColor(Color.parseColor("#FF2E2E2E"))
                                                    binding.tc3.setTextColor(Color.parseColor("#FF2E2E2E"))
                                                }
                                            })
                                            return false
                                        }
                                        override fun onLoadFailed(
                                            e: GlideException?,
                                            model: Any?,
                                            target: Target<GifDrawable?>?,
                                            isFirstResource: Boolean
                                        ): Boolean {
                                            return false
                                        }
                                    }).into(binding.ivBackground)
                            }
                            "2" -> {
                                Glide.with(this@MainPage)
                                    .asGif()
                                    .load(R.drawable.n2)
                                    .transition(DrawableTransitionOptions.withCrossFade())
                                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                    .listener(object : RequestListener<GifDrawable?> {
                                        override fun onResourceReady(
                                            resource: GifDrawable?,
                                            model: Any?,
                                            target: Target<GifDrawable?>?,
                                            dataSource: DataSource?,
                                            isFirstResource: Boolean
                                        ): Boolean {
                                            resource?.setLoopCount(1)
                                            resource?.registerAnimationCallback(object :
                                                Animatable2Compat.AnimationCallback() {
                                                override fun onAnimationEnd(drawable: Drawable?) {
                                                    Glide.with(this@MainPage).asGif()
                                                        .load(R.drawable.n3)
                                                        .transition(DrawableTransitionOptions.withCrossFade())
                                                        .into(binding.ivBackground)
                                                    binding.owner.setTextColor(Color.WHITE)
                                                    binding.tvRoom.setTextColor(Color.WHITE)
                                                    binding.tc1.setTextColor(Color.WHITE)
                                                    binding.tc2.setTextColor(Color.WHITE)
                                                    binding.tc3.setTextColor(Color.WHITE)
                                                }
                                            })
                                            return false
                                        }
                                        override fun onLoadFailed(
                                            e: GlideException?,
                                            model: Any?,
                                            target: Target<GifDrawable?>?,
                                            isFirstResource: Boolean
                                        ): Boolean {
                                            return false
                                        }
                                    }).into(binding.ivBackground)
                            }
                        }
                    }
                }
            }
        }
    }


    override fun getLayout() = R.layout.fragment_main_page

}