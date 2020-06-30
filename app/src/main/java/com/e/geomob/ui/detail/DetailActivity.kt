package com.e.geomob.ui.detail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.e.geomob.R
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class DetailActivity : AppCompatActivity()  , KodeinAware{

    override val kodein by closestKodein()
     var countryId : Int  = -1


    private val viewModel : DetailsViewModel by instance<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
      //  setSupportActionBar(toolbar)
        countryId = intent.getIntExtra("Country_Id", -1)
        if(countryId != -1){
            viewModel.getCountry(countryId)
        }
        println("package :" +getPackageName())

        val navigationBar = findViewById<BottomNavigationView>(R.id.bottom_navigation_bar)

        val navController = findNavController(R.id.nav_host_fragment)

        navigationBar.setupWithNavController(navController)
    }
}
