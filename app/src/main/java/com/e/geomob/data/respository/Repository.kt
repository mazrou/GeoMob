package com.e.geomob.data.respository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.e.geomob.Debug.TAG
import com.e.geomob.data.dataBase.GeoMobDao
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.MediaObject
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import com.e.geomob.ui.data.model.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Repository(
    private val geoMobDao: GeoMobDao
) {


    private val _countries = MutableLiveData<List<Country>>()

    val countries : LiveData<List<Country>>
        get() =_countries


    private val _country = MutableLiveData<Country>()

    val country : LiveData<Country>
        get()=_country


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







    fun getAllCountries() {
            CoroutineScope(IO).launch {
                delay(1000)
                _countries.postValue(geoMobDao.getAllCountries())
            }
    }

    fun initDataBase(
        countries: List<Country>
    )
    {
       val job =  CoroutineScope(IO).launch {
            for (country in countries) {
                // for parallel access to the data base
                launch {
                    geoMobDao.initDataBase(country)
                }
            }
        }

    }

    fun initHistory(
        histories :List<HistoricalEvent>
    ){
        val job =  CoroutineScope(IO).launch {
            for (history in histories) {
                // for parallel access to the data base
                launch {
                    geoMobDao.initHistoricalEvents(history)
                }
            }
        }
    }

    fun initPersonality(
        personalities :List<Personality>
    ){
        val job =  CoroutineScope(IO).launch {
            for ( personality in personalities) {
                // for parallel access to the data base
                launch {
                    geoMobDao.initPersonality(personality)
                }
            }
        }
    }

    fun initMedia(
        medias :List<MediaObject>
    ){
        val job =  CoroutineScope(IO).launch {
            for ( media in medias) {
                // for parallel access to the data base
                launch {
                    geoMobDao.initMediaObject(media)
                }
            }
        }
    }

    fun initSlideShow(
        slides :List<SlideItem>
    ){
        val job =  CoroutineScope(IO).launch {
            for ( slide in slides) {
                // for parallel access to the data base
                launch {
                    geoMobDao.initSlideShow(slide)
                }
            }
        }
    }

    fun getCountryById(countryId: Int) {

        CoroutineScope(IO).launch {
           launch {
               _country.postValue(geoMobDao.getCountryById(countryId))
           }
            launch {
                _media.postValue(geoMobDao.getVideos(countryId))
            }
            launch {
                _history.postValue(geoMobDao.getHistoricalEvent(countryId))
            }
            launch {
                _personality.postValue(geoMobDao.getPersonalities(countryId))
            }
            launch {
                _slide.postValue(geoMobDao.getSlidShow(countryId))
            }
        }

    }


}
