package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "personality" ,
    foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("country_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )])
data class Personality(

    @PrimaryKey
    val id : Int ,
    val country_id : Int,
    val image : Int ,
    val name : String ,
    val birthDay : String ,
    val history : String
)