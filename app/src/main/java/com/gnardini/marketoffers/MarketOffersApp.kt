package com.gnardini.marketoffers

import android.app.Application
import android.content.Context

import com.estimote.coresdk.common.config.EstimoteSDK
import com.gnardini.marketoffers.injectors.CommonInjector
import com.gnardini.marketoffers.injectors.RepositoryInjector

class MarketOffersApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        EstimoteSDK.initialize(applicationContext, "market-discounts-b8n", "e419a9c8f6ef04b92e271d80c4210d5f")
    }

    companion object {

        lateinit var appContext: Context
            private set

        val commonInjector by lazy { CommonInjector(repositoryInjector) }
        val repositoryInjector by lazy { RepositoryInjector() }
    }

}
