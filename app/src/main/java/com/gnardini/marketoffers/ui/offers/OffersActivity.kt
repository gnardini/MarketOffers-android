package com.gnardini.marketoffers.ui.offers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.injectors.commonInjector
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.Offer
import com.gnardini.marketoffers.offers.OffersListener

class OffersActivity : AppCompatActivity(), OffersListener {

    private val offersList : RecyclerView by bindView(R.id.offers_list)
    private val offersTracker by lazy { commonInjector.createOffersTracker(this) }

    private lateinit var offersAdapter : OffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        offersTracker.start()
        setupOffersList()
    }

    private fun setupOffersList() {
        offersAdapter = OffersAdapter()
        offersList.adapter = offersAdapter
        offersList.layoutManager = LinearLayoutManager(this)
    }

    override fun onOffersReady(offers: List<Offer>) {
        offersAdapter.updateOffers(offers)
    }

    override fun onResume() {
        super.onResume()
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        offersTracker.destroy()
    }

}
