package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.geomob.ui.data.model.Country
import java.sql.Date

@Entity(tableName = "personality")
data class Personality(

    @PrimaryKey
    val id : Int ,
    val country_id: Int?,
    val image : Int ,
    val name : String ,
    val birthDay : String ,
    val history : String

)