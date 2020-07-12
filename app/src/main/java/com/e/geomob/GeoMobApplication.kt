package com.e.geomob

import android.app.Application
import com.e.geomob.data.dataBase.GeoMobDataBase
import com.e.geomob.data.network.WikiApi
import com.e.geomob.data.respository.Repository
import com.e.geomob.ui.detail.DetailsViewModel
import com.e.geomob.ui.main.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class GeoMobApplication : Application() , KodeinAware {

    override val kodein = Kodein.lazy{
        import(androidXModule(this@GeoMobApplication))
        bind<GeoMobDataBase>() with singleton { GeoMobDataBase(instance()) }
        bind() from  singleton { WikiApi() }
        bind<Repository>() with  singleton { Repository(instance<GeoMobDataBase>().geoMob() , instance()) }
        bind<MainViewModel>() with singleton { MainViewModel(instance()) }
        bind<DetailsViewModel>() with singleton { DetailsViewModel(instance()) }

    }
}