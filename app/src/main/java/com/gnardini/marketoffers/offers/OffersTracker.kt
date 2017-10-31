package com.gnardini.marketoffers.offers

import android.content.Context
import com.estimote.coresdk.observation.region.RegionUtils
import com.estimote.coresdk.recognition.packets.EstimoteLocation
import com.estimote.coresdk.service.BeaconManager
import com.gnardini.marketoffers.repository.BeaconsRepository

class OffersTracker(
        private val appContext: Context,
        private val beaconsRepository: BeaconsRepository,
        private val offersListener: OffersListener) {

    private val MAX_DISTANCE = 1f
    private val TOLERANCE_MILLIS = 15 * 1000

    private val beaconManager = BeaconManager(appContext)
    private val lastAppearance = mutableMapOf<String, Long>()

    fun start() {
        beaconManager.connect { beaconManager.startLocationDiscovery() }
        beaconManager.setLocationListener(this::processBeacons)
    }

    private fun processBeacons(beacons: List<EstimoteLocation>) {
        beacons.forEach { println("Id: ${it.id}. Distance: ${RegionUtils.computeAccuracy(it)}") }
        val offers = beacons
                .filter { RegionUtils.computeAccuracy(it) <= MAX_DISTANCE  }
                .map { it.id.toHexString() }
                .apply(this::updateTimes)
                .plus(recentBeacons())
                .distinct()
                .mapNotNull { beaconsRepository.offerFromBeacon(it) }
                .sortedBy { it.id }

        offersListener.onOffersReady(offers)
    }

    private fun updateTimes(ids: List<String>) {
        val currentTime = System.currentTimeMillis()
        ids.forEach { lastAppearance[it] = currentTime }
    }

    private fun recentBeacons() : List<String> {
        val currentTime = System.currentTimeMillis()
        return lastAppearance
                .filterValues { it + TOLERANCE_MILLIS > currentTime }
                .keys
                .toList()
    }

    fun destroy() {
        beaconManager.disconnect()
    }

}