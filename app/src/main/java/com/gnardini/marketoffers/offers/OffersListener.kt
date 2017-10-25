package com.gnardini.marketoffers.offers

import com.gnardini.marketoffers.model.Offer

interface OffersListener {

    fun onOffersReady(offers: List<Offer>)

}