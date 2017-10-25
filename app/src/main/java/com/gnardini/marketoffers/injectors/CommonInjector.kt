package com.gnardini.marketoffers.injectors

import android.content.Context
import com.gnardini.marketoffers.MarketOffersApp
import com.gnardini.marketoffers.offers.OffersListener
import com.gnardini.marketoffers.offers.OffersTracker

class CommonInjector(
        private val repositoryInjector: RepositoryInjector) {

    fun getApplicationContext(): Context =
        MarketOffersApp.appContext

    fun createOffersTracker(offersListener: OffersListener): OffersTracker =
            OffersTracker(
                    getApplicationContext(),
                    repositoryInjector.beaconsRepository,
                    offersListener)

}
