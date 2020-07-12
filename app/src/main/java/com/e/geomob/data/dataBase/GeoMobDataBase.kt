package com.e.geomob.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.geomob.data.model.*
import com.e.geomob.data.model.Country

@Database(
    version = 2,
    entities = [Country::class,
                HistoricalEvent::class ,
                Personality::class ,
                SlideItem::class ,
                Tweet::class ,
                Resource::class,
                YoutubeVideo::class]
)
abstract class GeoMobDataBase :RoomDatabase() {

    abstract fun geoMob() : GeoMobDao

    companion object{

        private const val DATABASE_NAME = "geoMobile.db"
        @Volatile private  var instance  : GeoMobDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }
        private fun buildDatabase(context: Context)  =
            Room.databaseBuilder(context.applicationContext,
                     GeoMobDataBase::class.java , DATABASE_NAME)
                .build()

    }
}