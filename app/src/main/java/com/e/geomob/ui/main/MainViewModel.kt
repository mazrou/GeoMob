package com.e.geomob.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.geomob.Debug
import com.e.geomob.R
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.MediaObject
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import com.e.geomob.data.respository.Repository
import com.e.geomob.ui.data.model.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: Repository
) : ViewModel() {


    init {
        repository.countries.observeForever {
            Log.d(Debug.TAG , it.toString())
            _countries.postValue(it)
        }
    }

    private val _countries = MutableLiveData<List<Country>>()

    val countries : LiveData<List<Country>>
        get() =_countries


    fun initDataBase(){

            initCountries()
            initMediaObjects()
            initHistoricalEvents()
            initPersonalities()
            initSlideShows()
    }

    private fun initSlideShows() {
        val images = ArrayList<SlideItem>()
        images.add(
            SlideItem(1, 1, R.raw.germany_history)
        )
        images.add(
            SlideItem(2, 1, R.raw.germany_history)
        )
        images.add(
            SlideItem(4, 1, R.raw.germany_history)
        )
        images.add(
            SlideItem(3, 1, R.raw.germany_history)
        )
        repository.initSlideShow(images)
    }
    private fun initMediaObjects(){
        val list  = ArrayList<MediaObject>()
        MediaObject()
        list.add(
            MediaObject(
                1,
                "Sending Data to a New Activity with Intent Extras",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1" ,
                1
            )
        )
        list.add(
            MediaObject(
                2,
                "Sending Data to a New Activity with Intent Extras",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1",
                1
            )
        )
        list.add(
            MediaObject(
                3,
                "Sending Data to a New Activity with Intent Extras",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1",
                2
            )
        )

        repository.initMedia(list)
    }
    private fun initCountries(){
       val list  = ArrayList<Country>()
       list.add(Country(1, "Brazil" , R.raw.brazil))
       list.add(Country(2, "China" , R.raw.china))
       list.add(Country(3, "Germany" , R.raw.germany))
       list.add(Country(4, "Italy" , R.raw.italy))
       list.add(Country(5, "Egypt" , R.raw.egypt))
       list.add(Country(6, "Algeria" , R.raw.algeria))
       list.add(Country(7, "tunisia" , R.raw.tunisia))
       Log.d(Debug.TAG , "we have add ${list[0].anthem}" )
       repository.initDataBase(list)
   }
    private fun initHistoricalEvents(){
        val listHistory = ArrayList<HistoricalEvent>()
        listHistory.add(
            HistoricalEvent(1, 1 , "The second world war " , "1939", "This is the word karitha ta3 denya yawedi makach menha lksdjas jsdlkg jfds oisgjlk fdsjg poirö-l", R.raw.germany_history)
        )
        listHistory.add(
            HistoricalEvent(2, 1, "The second world war " , "1939", "This is the word karitha ta3 denya yawedi makach menha lksdjas jsdlkg jfds oisgjlk fdsjg poirö-l", R.raw.germany_history)
        )
        repository.initHistory(listHistory)

    }
    private fun initPersonalities(){
        val listPresonality = ArrayList<Personality>()
        listPresonality.add(
            Personality(1,2 , R.raw.germany_history , "Hitler" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k")
        )
        listPresonality.add(
            Personality(2,1 , R.raw.germany_history , "Hitler" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k")
        )
        listPresonality.add(
            Personality(3,1 , R.raw.germany_history , "Hitler" , "34,4324" , "tirrosirtld adsfklj lk jsadfio saödf k")
        )
        repository.initPersonality(listPresonality)
    }


    fun getCountries() {
       repository.getAllCountries()
    }

}
