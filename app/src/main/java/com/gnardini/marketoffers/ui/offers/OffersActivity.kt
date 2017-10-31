package com.gnardini.marketoffers.ui.offers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.extensions.commonInjector
import com.gnardini.marketoffers.extensions.repositoryInjector
import com.gnardini.marketoffers.extensions.startActivity
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.Offer
import com.gnardini.marketoffers.offers.OffersListener

class OffersActivity : AppCompatActivity(), OffersListener {

    private val offersList by bindView<RecyclerView>(R.id.offers_list)
    private val seeAcceptedOffers by bindView<View>(R.id.see_accepted_offers)
    private val offersTracker by lazy { commonInjector.createOffersTracker(this) }
    private val beaconsRepository by lazy { repositoryInjector.beaconsRepository }

    private lateinit var offersAdapter : OffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.offers_activity)
        beaconsRepository.loadBeacons()
        offersTracker.start()
        setupOffersList()
        seeAcceptedOffers.setOnClickListener { startActivity(AcceptedOffersActivity::class.java) }
    }

    private fun setupOffersList() {
        offersAdapter = OffersAdapter(beaconsRepository)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.offers_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId ?: -1
//        when (itemId) {
//            R.id.checkout -> startActivity(ShowQrActivity::class.java)
//            R.id.scan -> startActivity(ScanQrActivity::class.java)
//        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        offersTracker.destroy()
    }

}
