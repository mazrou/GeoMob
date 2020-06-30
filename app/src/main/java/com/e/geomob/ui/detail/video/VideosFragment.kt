package com.e.geomob.ui.detail.video

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.ui.recyclerView.VideoPlayerRecyclerAdapter
import com.e.geomob.data.model.MediaObject
import com.e.geomob.ui.detail.DetailsViewModel
import com.e.geomob.ui.recyclerView.util.VerticalSpacingItemDecorator
import kotlinx.android.synthetic.main.fragment_videos.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.ArrayList

class VideosFragment() : Fragment(R.layout.fragment_videos) , KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModel : DetailsViewModel by instance<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list : List<MediaObject>? = null

        viewModel.media.observe(viewLifecycleOwner, Observer { videos ->
            list = videos
            println(Debug.TAG + list!!.size)
            val adapter =  VideoPlayerRecyclerAdapter(list as ArrayList<MediaObject>,initGlide())

            recycler_view.also {
                it.adapter =adapter
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.setMediaObjects(list as ArrayList<MediaObject>)
                it.addItemDecoration(
                    VerticalSpacingItemDecorator(20)
                )
            }
        })


    }


    private fun initGlide(): RequestManager? {
        val options = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }
}
