package com.e.geomob.ui.detail.home


import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blongho.country_data.World
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import com.e.geomob.ui.data.model.Country
import com.e.geomob.ui.detail.DetailsViewModel
import com.e.geomob.ui.main.CountryListDecorator
import kotlinx.android.synthetic.main.country_list_item.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_videos.*

import kotlinx.android.synthetic.main.layout_home_fragment.*
import kotlinx.android.synthetic.main.layout_home_fragment2.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class HomeFragment : Fragment(R.layout.fragment_home) , KodeinAware {

    override val kodein by closestKodein()

    private val viewModel : DetailsViewModel by instance<DetailsViewModel>()

    private var mediaPlayer : MediaPlayer? = null

    private  var historyAdapter : HistoryAdapter? = null

    private var historyList : List<HistoricalEvent>? = null

    private  var personalityAdapter : PersonalityAdapter? = null

    private var personalityList : List<Personality>? = null

    private var slide : List<SlideItem> = ArrayList<SlideItem>()

    var isPlaying : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setupViewFlipper(slide)
    }

    private fun setupViewFlipper (images : List<SlideItem>){
        for (image in images){
            flipperImages(image.image)
        }
    }

    private fun subscribeObservers(){

        viewModel.country.observe(viewLifecycleOwner , Observer {
                setHeader(it)
        })

        viewModel.history.observe(viewLifecycleOwner , Observer {
           println(Debug.TAG + it.toString())
            if (historyAdapter == null){
                historyAdapter  = HistoryAdapter(it)
            }
            historyList = it
            historyAdapter!!.setList(it)

            history_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = historyAdapter
                it.setHasFixedSize(true)
                it.addItemDecoration(CountryListDecorator(10))
            }
        })

        viewModel.personality.observe(viewLifecycleOwner , Observer {
            if(personalityAdapter == null)
            {
                personalityAdapter = PersonalityAdapter(it)
            }
            personalityList = it
            personality_recyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = personalityAdapter
                it.setHasFixedSize(true)
                it.addItemDecoration(CountryListDecorator(10))
            }

        })

        viewModel.slide.observe(viewLifecycleOwner , Observer {
            slide = it
            setupViewFlipper(slide)
        })


    }


    private fun setHeader(country: Country){

        // set the flag
        flag_image.setImageResource(World.getFlagOf(country.name))

        // set the country name
        country_name.text = country.name


        audio_button.setOnClickListener{

            isPlaying = if(isPlaying){
                audio_button.setImageResource(R.drawable.ic_action_name)

                mediaPlayer?.pause()

                false
            }else{

                if(mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(requireContext() , country.anthem)
                }
                audio_button.setImageResource(R.drawable.ic_stop_name)

                mediaPlayer?.start()
                true
            }
        }
    }

    private fun flipperImages( image : Int){
        val imageView = ImageView(requireContext())
        imageView.setImageResource(image)

        viewFlipper.addView(imageView)
        viewFlipper.flipInterval = 3000
        viewFlipper.isAutoStart = true


        //Animation
        viewFlipper.setInAnimation(requireContext(),android.R.anim.slide_in_left )
        viewFlipper.setOutAnimation(requireContext(),android.R.anim.slide_out_right )
    }
}
