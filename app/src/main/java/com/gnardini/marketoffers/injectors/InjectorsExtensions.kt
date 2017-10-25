package com.gnardini.marketoffers.injectors

import android.support.v7.app.AppCompatActivity
import com.gnardini.marketoffers.MarketOffersApp

val AppCompatActivity.commonInjector: CommonInjector
    get() = MarketOffersApp.commonInjector

val AppCompatActivity.repositoryInjector: RepositoryInjector
    get() = MarketOffersApp.repositoryInjector
