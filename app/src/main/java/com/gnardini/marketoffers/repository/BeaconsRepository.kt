package com.gnardini.marketoffers.repository

import com.gnardini.marketoffers.model.Offer
import com.gnardini.marketoffers.repository.services.BeaconsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeaconsRepository(private val beaconsService: BeaconsService,
                        private val userRepository: UserRepository) {

    private val beacons = mutableMapOf<String, Offer>()

    fun loadBeacons() {
        beaconsService
                .fetchOffers(userRepository.getLoggedUserId())
                .enqueue(object : Callback<Map<String, Offer>> {

                    override fun onResponse(call: Call<Map<String, Offer>>?, response: Response<Map<String, Offer>>) {
                        if (response.isSuccessful) {
                            response.body()?.let { beacons.putAll(it) }
                        } else {
                            tryLoadingAgain()
                        }
                    }

                    override fun onFailure(call: Call<Map<String, Offer>>?, t: Throwable?) {
                        tryLoadingAgain()
                    }
                })
    }

    fun tryLoadingAgain() {
        // TODO Retry loading
    }

    fun acceptOffer(offer: Offer) {
        beaconIdFromOffer(offer)?.let {
            beacons[it] = offer.copy(accepted = true)
        }
    }

    fun offerFromBeacon(id: String) = beacons[id]

    private fun beaconIdFromOffer(offer: Offer): String? {
        beacons.entries.forEach {
            if (it.value.id == offer.id) {
                return it.key
            }
        }
        return null
    }

    fun getAcceptedOffers() = beacons.values.filter { it.accepted }

}