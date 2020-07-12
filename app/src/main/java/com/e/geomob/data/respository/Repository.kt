package com.e.geomob.data.respository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.geomob.Debug
import com.e.geomob.data.dataBase.GeoMobDao
import com.e.geomob.data.model.*
import com.e.geomob.data.network.WikiApi
import com.e.geomob.data.network.model.PageContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Repository(
    private val geoMobDao: GeoMobDao ,
    private val api : WikiApi
) {


    private val _countries = MutableLiveData<List<Country>>()

    val countries : LiveData<List<Country>>
        get() =_countries


    private val _country = MutableLiveData<Country>()

    val country : LiveData<Country>
        get()=_country

    private val _history = MutableLiveData<List<HistoricalEvent>>()

    val history : LiveData<List<HistoricalEvent>>
        get()=_history

    private val _personality = MutableLiveData<List<Personality>>()

    val personality : LiveData<List<Personality>>
        get()=_personality

    private val _slide = MutableLiveData<List<SlideItem>>()
    val slide : LiveData<List<SlideItem>>
        get()=_slide

    private val _resource = MutableLiveData<List<Resource>>()
    val resource : LiveData<List<Resource>>
        get()=_resource

    private val _description = MutableLiveData<PageContent>()

    val description : LiveData<PageContent>
        get() = _description

    val youtubeVideo : LiveData<List<YoutubeVideo>>
        get() = _youtubeVideo
    private val _youtubeVideo  = MutableLiveData<List<YoutubeVideo>>()

    fun getAllCountries() {
            CoroutineScope(IO).launch {
                delay(1000)
                _countries.postValue(geoMobDao.getAllCountries())
            }
    }

    fun initDataBase(
        countries: List<Country>
    ) {
         CoroutineScope(IO).launch {
           geoMobDao.initDataBase(countries)
        }
    }

    fun initResources(
        resources : List<Resource>
    ){
        CoroutineScope(IO).launch {
            geoMobDao.initResource(resources)
        }
    }

    fun initYoutubeVideos(
        videos: List<YoutubeVideo>
    ){
        CoroutineScope(IO).launch {
            geoMobDao.initYoutubeVideos(videos)
        }
    }

    fun initHistory(histories :List<HistoricalEvent>)
    {
         CoroutineScope(IO).launch {
            geoMobDao.initHistoricalEvents(histories)
            Log.d(Debug.TAG , "initHistory from Repository")
        }
    }

    fun initPersonality(
        personalities :List<Personality>
    ){
          CoroutineScope(IO).launch {
            geoMobDao.initPersonality(personalities)
            Log.d(Debug.TAG , "initPersonality from Repository")
        }
    }

    fun initSlideShow(
        slides :List<SlideItem>
    ){
         CoroutineScope(IO).launch {
            geoMobDao.initSlideShow(slides)
            Log.d(Debug.TAG , "initSlideShow from Repository")
        }
    }


    suspend fun getDescriptionFromWiki( countryName: String) : PageContent{
        Log.d(Debug.TAG , "got data from Wiki APi")

        val wikiResponse  = api.getDescription(countryName =countryName)
       return wikiResponse.body()!!.query.pages.page
    }

    fun getCountryById(countryId: Int) {

        CoroutineScope(IO).launch {
           launch {
               _country.postValue(geoMobDao.getCountryById(countryId))
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
            launch {
                _resource.postValue(geoMobDao.getResources(countryId))
            }

            launch {
                _youtubeVideo.postValue(geoMobDao.getYoutubeVideos(countryId))
            }
        }

    }


}
