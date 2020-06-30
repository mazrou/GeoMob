package com.e.geomob.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.geomob.Debug
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.MediaObject
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem

import com.e.geomob.data.respository.Repository
import com.e.geomob.ui.data.model.Country

class DetailsViewModel (
    private val repository: Repository
){

    init {
        repository.country.observeForever {
            Log.d(Debug.TAG , it.toString())
            _country.postValue(it)
        }
        repository.history.observeForever {
            Log.d(Debug.TAG , it.toString())
            _history.postValue(it)
        }

        repository.media.observeForever {
            Log.d(Debug.TAG , it.toString())
            _media.postValue(it)
        }

        repository.personality.observeForever {
            Log.d(Debug.TAG , it.toString())
            _personality.postValue(it)
        }

        repository.slide.observeForever {
            Log.d(Debug.TAG , it.toString())
           _slide.postValue(it)
        }

    }

    private val _media = MutableLiveData<List<MediaObject>>()

    val media : LiveData<List<MediaObject>>
        get()=_media


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

    fun getCountry(countryId : Int) = repository.getCountryById(countryId)

}