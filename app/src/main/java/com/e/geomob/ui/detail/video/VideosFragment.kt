package com.e.geomob.ui.detail.video

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.data.model.YoutubeVideo
import com.e.geomob.ui.detail.DetailsViewModel
import com.e.geomob.ui.main.CountryListDecorator

import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.ArrayList

class VideosFragment() : Fragment(R.layout.fragment_videos) , KodeinAware {


    override val kodein by closestKodein()

    private val viewModel : DetailsViewModel by instance()

    val adapter  = VideoRecyclerViewAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeObserver()
        initRecyclerView()
    }

    private fun subscribeObserver(){
        viewModel.youtubeVideo.observe(viewLifecycleOwner , Observer {
            adapter.submitList(it)
        })
    }

    private fun initRecyclerView (){
        video_recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter  = adapter
            it.setHasFixedSize(true)
            it.addItemDecoration(CountryListDecorator(18))
        }
    }



}
