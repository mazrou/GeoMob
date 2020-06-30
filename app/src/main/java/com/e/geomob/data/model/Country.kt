package com.e.geomob.ui.data.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore.Images
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.geomob.data.model.HistoricalEvent
import com.e.geomob.data.model.Personality
import org.w3c.dom.Text


@Entity(tableName = "country")
data class Country(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int ,

    @ColumnInfo(name = "name")
    val name : String ,

    @ColumnInfo(name = "anthem")
    val anthem : Int,

    @ColumnInfo(name = "description")
    val description : String? = null ,

    @ColumnInfo(name = "population")
    val population : Float? = null ,

    @ColumnInfo(name = "area")
    val area : Float? =null
/*
    val slideShow : List<Int>? = null ,



    val personalities : List<Personality>? = null,

    val HistoricalEvents  : List<HistoricalEvent> ? =null

*/
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Country
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        return result
    }

}
