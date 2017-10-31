package com.gnardini.marketoffers.ui.offers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.extensions.repositoryInjector
import com.gnardini.marketoffers.kotterknife.bindView

class AcceptedOffersActivity : AppCompatActivity() {

    private val offersList by bindView<RecyclerView>(R.id.accepted_offers_list)
    private val beaconsRepository by lazy { repositoryInjector.beaconsRepository }
    private lateinit var offersAdapter : AcceptedOffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accepted_offers_activity)
        setupAcceptedOffersList()
    }

    private fun setupAcceptedOffersList() {
        offersAdapter = AcceptedOffersAdapter(this, beaconsRepository.getAcceptedOffers())
        offersList.adapter = offersAdapter
        offersList.layoutManager = LinearLayoutManager(this)
    }

}
