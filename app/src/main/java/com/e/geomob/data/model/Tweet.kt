package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Tweet",
    foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("country_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Tweet (
    @PrimaryKey(autoGenerate = true)
    val idTweet: Long?,
    val userImg : String,
    val userName : String,
    val screenName : String,
    val img : String,
    val content : String,
    val country_id : Int
)