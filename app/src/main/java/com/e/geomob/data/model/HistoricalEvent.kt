package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "historical_event"
    ,foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("country_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
class HistoricalEvent(
    @PrimaryKey
    val  id : Int ,
    val country_id : Int ,
    val title: String,
    val description: String ,

    val date : String? = null,

    val image : Int? = null

)