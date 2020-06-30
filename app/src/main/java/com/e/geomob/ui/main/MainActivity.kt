package com.e.geomob.ui.main

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.e.geomob.R
import com.e.geomob.ui.data.model.Country
import com.e.geomob.ui.detail.DetailActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
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
