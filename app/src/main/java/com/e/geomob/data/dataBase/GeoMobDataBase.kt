package com.e.geomob.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.MediaObject
import com.e.geomob.data.model.Personality
import com.e.geomob.data.model.SlideItem
import com.e.geomob.ui.data.model.Country

@Database(
    version = 2,
    entities = [Country::class,
                HistoricalEvent::class ,
                MediaObject:: class ,
                Personality::class ,
                SlideItem::class]
)
abstract class GeoMobDataBase :RoomDatabase() {

    abstract fun geoMob() : GeoMobDao

    companion object{

        private const val DATABASE_NAME = "geoMob.db"
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