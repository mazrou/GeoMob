package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "resource" ,
        foreignKeys = [ForeignKey(
                entity = Country::class,
                parentColumns = arrayOf("country_id"),
                childColumns = arrayOf("country_id"),
                onDelete = ForeignKey.CASCADE)
        ])
data class Resource(
    @PrimaryKey(autoGenerate = false)
    val id : Int ,
    val country_id : Int ,
    val name : String ,
    val description : String
)