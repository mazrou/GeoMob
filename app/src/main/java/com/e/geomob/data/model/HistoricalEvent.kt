package com.e.geomob.data.model

import android.accounts.AuthenticatorDescription
import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.geomob.ui.data.model.Country
import java.sql.Date


@Entity(tableName = "historical_event")
class HistoricalEvent(
    @PrimaryKey
    val  id : Int ,
    val country_id:  Int,
    val title: String,
    val date : String,
    val description: String ,
    val image : Int? = null
)