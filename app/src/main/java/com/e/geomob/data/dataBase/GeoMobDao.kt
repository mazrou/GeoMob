package com.e.geomob.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Room
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.MediaObject
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import com.e.geomob.ui.data.model.Country

@Dao
interface GeoMobDao {

    @Query(
        "SELECT * FROM country"
    )
      fun getAllCountries() : List<Country>?

    @Insert(onConflict = REPLACE)
    fun initDataBase(
        countries : Country
    ) : Long

    @Query(
        "SELECT * FROM country WHERE id = :countryId"
    )
    fun getCountryById(countryId: Int) : Country

    @Insert(onConflict = REPLACE)
    fun initSlideShow(
        slideItem: SlideItem
    ) : Long


    @Insert(onConflict = REPLACE)
    fun initMediaObject(
        mediaObject: MediaObject
    ): Long


    @Insert(onConflict = REPLACE)
    fun initHistoricalEvents (
        historicalEvent: HistoricalEvent
    ) : Long

    @Insert(onConflict = REPLACE)
    fun initPersonality(
        personality: Personality
    ) :Long



    @Query(
        "SELECT * FROM historical_event WHERE country_id = :country"
    )
    fun getHistoricalEvent(
        country: Int
    ):List<HistoricalEvent>?


    @Query(
        "SELECT * FROM personality WHERE country_id = :country"
    )
    fun getPersonalities(
        country: Int
    ):List<Personality>?


    @Query(
        "SELECT * FROM video WHERE country_id = :country"
    )
    fun getVideos(
        country: Int
    ):List<MediaObject>?


    @Query(
        "SELECT * FROM slideShow WHERE country_id = :country"
    )
    fun getSlidShow(
        country: Int
    ):List<SlideItem>?
}