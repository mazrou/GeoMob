package com.e.geomob.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blongho.country_data.World
import com.e.geomob.R

import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity()  , KodeinAware {

    override val kodein by closestKodein()

    private val viewModel : MainViewModel by instance<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        //init the flags
        World.init(this.applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init the data base
        viewModel.initDataBase()
    }

}
