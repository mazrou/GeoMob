package com.e.geomob.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.e.geomob.ui.data.model.Country


@Entity(tableName = "slideShow")
data class SlideItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int ,

    @ColumnInfo(name = "country_id")
    val country_id : Int ,

    @ColumnInfo(name = "image")
    val image : Int
)