package com.gnardini.marketoffers.extensions

import android.support.v7.app.AppCompatActivity
import com.gnardini.marketoffers.MarketOffersApp
import com.gnardini.marketoffers.injectors.CommonInjector
import com.gnardini.marketoffers.injectors.RepositoryInjector

val AppCompatActivity.commonInjector: CommonInjector
    get() = MarketOffersApp.commonInjector

val AppCompatActivity.repositoryInjector: RepositoryInjector
    get() = MarketOffersApp.repositoryInjector
