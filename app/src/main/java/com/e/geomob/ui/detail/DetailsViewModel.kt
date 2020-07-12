package com.e.geomob.ui.detail

import android.graphics.pdf.PdfDocument
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.geomob.Debug
import com.e.geomob.data.model.*

import com.e.geomob.data.respository.Repository
import com.e.geomob.data.model.Country
import com.e.geomob.data.network.model.PageContent

class DetailsViewModel (
    private val repository: Repository
){

    private var loadingListener : OnLoadDataListener? = null

    init {
        repository.country.observeForever {
            Log.d(Debug.TAG , it.toString())
            _country.postValue(it)
        }
        repository.history.observeForever {
            Log.d(Debug.TAG , it.toString())
            _history.postValue(it)
        }

        repository.personality.observeForever {
            Log.d(Debug.TAG , it.toString())
            _personality.postValue(it)
        }

        repository.slide.observeForever {
            Log.d(Debug.TAG , it.toString())
           _slide.postValue(it)
        }

        repository.resource.observeForever {
            Log.d(Debug.TAG , it.toString())
            _resource.postValue(it)
        }

        repository.description.observeForever {
            _description.postValue(it)
        }

        repository.youtubeVideo.observeForever {
            _youtubeVideo.postValue(it)
        }
    }


    val youtubeVideo : LiveData<List<YoutubeVideo>>
        get() = _youtubeVideo
    private val _youtubeVideo  = MutableLiveData<List<YoutubeVideo>>()


    private val _history = MutableLiveData<List<HistoricalEvent>>()

    val history : LiveData<List<HistoricalEvent>>
        get()=_history

    private val _personality = MutableLiveData<List<Personality>>()

    val personality : LiveData<List<Personality>>
        get()=_personality



    private val _slide = MutableLiveData<List<SlideItem>>()
    val slide : LiveData<List<SlideItem>>
        get()=_slide


    private val _country  = MutableLiveData<Country>()

    val country : LiveData<Country>
        get() =  _country


    val tweets : LiveData<Tweet>
        get() = _tweets
    private val _tweets = MutableLiveData<Tweet>()


    val resource : LiveData<List<Resource>>
        get() = _resource
    private val _resource  = MutableLiveData<List<Resource>>()


    val description : LiveData<PageContent>
        get() = _description
    private val _description  = MutableLiveData<PageContent>()


    fun getCountry(countryId : Int) {
        loadingListener?.let {
            it.startLoading()
        }
        repository.getCountryById(countryId)
        loadingListener?.let {
            it.endLoading()
        }
    }

    fun setLoadingListener(onLoadDataListener: OnLoadDataListener) {
        loadingListener = onLoadDataListener
    }

}