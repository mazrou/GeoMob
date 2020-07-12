package com.e.geomob.data.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.e.geomob.data.model.*

@Dao
interface GeoMobDao {

    @Insert(onConflict = REPLACE)
    fun initDataBase(countries : List<Country>)

    @Insert(onConflict = REPLACE)
    fun initResource( resources : List<Resource>)

    @Insert(onConflict = REPLACE)
     fun initSlideShow(slideItem: List<SlideItem>)

    @Insert(onConflict = REPLACE)
     fun initHistoricalEvents (historicalEvent: List<HistoricalEvent>)

    @Insert(onConflict = REPLACE)
     fun initPersonality(personality: List<Personality>)

    @Insert(onConflict = REPLACE)
    fun initYoutubeVideos ( youtubeVideos: List<YoutubeVideo>)


    @Query("SELECT * FROM historical_event WHERE country_id = :country")
    fun getHistoricalEvent(country: Int):List<HistoricalEvent>?

    @Query("SELECT * FROM youtube_video WHERE country_id = :country")
    fun getYoutubeVideos(country: Int):List<YoutubeVideo>?

    @Query("SELECT * FROM country WHERE country_id = :countryId")
    fun getCountryById(countryId: Int) : Country

    @Query("SELECT * FROM country")
    fun getAllCountries() : List<Country>?

    @Query("SELECT * FROM personality WHERE country_id = :country")
    fun getPersonalities(country: Int):List<Personality>?

    @Query("SELECT * FROM slideShow WHERE country_id = :country")
    fun getSlidShow(country: Int):List<SlideItem>?

    @Query("SELECT * FROM resource WHERE country_id  = :country")
    fun getResources(country: Int) : List<Resource>?

}