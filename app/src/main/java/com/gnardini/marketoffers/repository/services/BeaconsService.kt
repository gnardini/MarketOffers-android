package com.gnardini.marketoffers.repository.services

import com.gnardini.marketoffers.model.Offer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BeaconsService {

    @GET("/fetch_offers")
    fun fetchOffers(@Query("user_id") userId: String) : Call<Map<String, Offer>>

}
