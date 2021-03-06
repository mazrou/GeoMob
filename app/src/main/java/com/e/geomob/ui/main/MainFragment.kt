package com.e.geomob.ui.main

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.data.model.Country
import com.e.geomob.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class MainFragment
    : Fragment(R.layout.fragment_main) ,
    CountryListAdapter.Interaction ,
    KodeinAware
{

    override val kodein by closestKodein()

    private lateinit var recyclerAdapter : CountryListAdapter
    var mediaPlayer: MediaPlayer? = null
    lateinit var countryListDecorator : CountryListDecorator

    lateinit var lastHolder : CountryListAdapter.CountryItemViewHolder
    private var listCounty : ArrayList<Country>? = ArrayList<Country>(1)

    private  val viewModel: MainViewModel by instance<MainViewModel>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getCountries()
        subscribeObserver()
        initRecyclerView()
    }


     private fun subscribeObserver(){
         viewModel.countries.observe(viewLifecycleOwner , Observer { it ->
             Log.d(com.e.geomob.Debug.TAG , "LiveData<Country> data change from MainFragment")
             listCounty = it as ArrayList<Country>?
           // if the list is not null
             listCounty?.let {list ->
                 recyclerAdapter.submitList(list)
             }
         })

     }
    private fun initRecyclerView(){
        Log.d(com.e.geomob.Debug.TAG , "init the recyclerView from MainFragment")
        recyclerAdapter = CountryListAdapter(this)
        countryListDecorator = CountryListDecorator(15)
        country_list?.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = recyclerAdapter
            it.addItemDecoration(countryListDecorator)
        }
    }

    override fun onItemSelected(position: Int, item: Country) {
        Log.d(Debug.TAG , "onItemSelected  : Start DetailActivity")
       val intent : Intent  = Intent(requireContext() , DetailActivity::class.java)
        intent.putExtra("Country_Id" , item.country_id)
        startActivity(intent)
    }

    override fun mediaPlayerButtonListener(position: Int, item: Country, holder : CountryListAdapter.CountryItemViewHolder) {
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(requireContext() , item.anthem)
            mediaPlayer?.start()
            lastHolder= holder
        }else{
            if (lastHolder == holder){
                if (lastHolder.isPlaying) mediaPlayer!!.start()
                else mediaPlayer!!.pause()
            }else{
                if (lastHolder.isPlaying)
                    lastHolder.playOrPause()
                mediaPlayer!!.release()
                mediaPlayer  = MediaPlayer.create(requireContext() , item.anthem)
                mediaPlayer?.start()
                lastHolder= holder
            }
        }
    }

}
