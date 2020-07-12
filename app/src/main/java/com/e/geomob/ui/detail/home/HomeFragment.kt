package com.e.geomob.ui.detail.home


import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blongho.country_data.World
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import com.e.geomob.data.model.Country
import com.e.geomob.ui.detail.DetailsViewModel
import com.e.geomob.ui.detail.home.adapter.HistoryAdapter
import com.e.geomob.ui.detail.home.adapter.ImageAdapter
import com.e.geomob.ui.detail.home.adapter.PersonalityAdapter
import com.e.geomob.ui.detail.home.adapter.ResourceAdapter
import com.e.geomob.ui.main.CountryListDecorator
import kotlinx.android.synthetic.main.country_list_item.*
import kotlinx.android.synthetic.main.country_list_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class HomeFragment : Fragment(R.layout.fragment_home) , KodeinAware {

    override val kodein by closestKodein()

    private val viewModel : DetailsViewModel by instance<DetailsViewModel>()

    private var mediaPlayer : MediaPlayer? = null

    private  var historyAdapter : HistoryAdapter? = null

    private var imageAdapter : ImageAdapter? = null

    private  var personalityAdapter : PersonalityAdapter? = null

    private val resourceAdapter = ResourceAdapter()


    var isPlaying : Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initRecyclerViews()
    }


    private fun subscribeObservers(){
        historyAdapter = HistoryAdapter()
        personalityAdapter  = PersonalityAdapter()
        imageAdapter = ImageAdapter()

        viewModel.country.observe(viewLifecycleOwner , Observer {
            setHeader(it)
            surfaceView.text = "${it.area.toString()} million km²"
            populationView.text = "${it.population.toString()} millions"
            description_text_view.text = it.description
            capital.text = it.capital
            currency.text = it.currency
        })


        viewModel.history.observe(viewLifecycleOwner , Observer {
            historyAdapter!!.setList(it)
        })

        viewModel.resource.observe(viewLifecycleOwner , Observer {
                resourceAdapter.submitList(it)
        })

        viewModel.personality.observe(viewLifecycleOwner , Observer {
            println("DebugApp set personalities")
            personalityAdapter!!.submitList(it)
        })

        viewModel.slide.observe(viewLifecycleOwner , Observer {list->

            Log.d(Debug.TAG , list.toString())
            imageAdapter!!.submitList(list)

        })
    }


    private fun initRecyclerViews(){

        recyclerResources.also {
            it.layoutManager  = LinearLayoutManager(requireContext())
            it.adapter = resourceAdapter
            it.addItemDecoration(CountryListDecorator(10))
            it.setHasFixedSize(true)
        }

        recyclerHistory.also {
            it.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            it.adapter = historyAdapter
            it.setHasFixedSize(true)
            it.addItemDecoration(CountryListDecorator(10))
        }

        recyclerPersons.also {
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.adapter = personalityAdapter
            it.setHasFixedSize(true)
            it.addItemDecoration(CountryListDecorator(10))
        }

        recyclerPhotos.also {
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.adapter = imageAdapter
            it.setHasFixedSize(true)
            it.addItemDecoration(CountryListDecorator(20))
        }



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
}
