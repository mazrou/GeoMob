package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "youtube_video"
    ,foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("country_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class YoutubeVideo(
    @PrimaryKey
    val id : Int ,
    val videoUrl : String,
    val country_id : Int
)