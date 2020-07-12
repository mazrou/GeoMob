package com.e.geomob.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "country")
data class Country(
    @PrimaryKey
    @ColumnInfo(name = "country_id")
    val country_id : Int ,

    @ColumnInfo(name = "name")
    val name : String ,

    @ColumnInfo(name = "anthem")
    val anthem : Int,

    @ColumnInfo(name = "description")
    val description : String? = null ,

    @ColumnInfo(name = "population")
    val population : Float? = null ,

    @ColumnInfo(name = "area")
    val area : Float? =null ,

    val capital : String ?=null,
    val currency : String?=null ,
    val tweet: String? = null

) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Country
        if (country_id != other.country_id) return false
        return true
    }

    override fun hashCode(): Int {
        var result = country_id
        result = 31 * result + name.hashCode()
        return result
    }
}
