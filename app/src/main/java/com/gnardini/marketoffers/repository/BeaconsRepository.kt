package com.gnardini.marketoffers.repository

import com.gnardini.marketoffers.model.Offer

class BeaconsRepository {

    val beacons = mutableMapOf<String, Offer>()

    init {
        beacons["8a21138990e1ebcd058f6b8485d13e31"] = Offer("Cola Cola 2L", 20f)
        beacons["35393280630e846f9e9dafd54785550b"] = Offer("Pepsi 2L", 16f)
        beacons["8fc235de0ae8970001cc12102f6e3b0e"] = Offer("Agua Sierra de los Padres 2L", 10f)
    }

    fun offerFromBeacon(id: String) = beacons[id]

//    {
//        val offer = beacons[id]
//        if (offer == null) {
//            callback.onError("No beacon registered with that ID")
//        } else {
//            callback.onSuccess(offer)
//        }
//    }

}