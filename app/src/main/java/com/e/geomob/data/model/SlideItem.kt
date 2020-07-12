package com.e.geomob.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "slideShow"
    ,foreignKeys = [ForeignKey(
        entity = Country::class,
        parentColumns = arrayOf("country_id"),
        childColumns = arrayOf("country_id"),
        onDelete = ForeignKey.CASCADE
    )])
data class SlideItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int ,
    val country_id : Int,

    @ColumnInfo(name = "image")
    val image : Int
)