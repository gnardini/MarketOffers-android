package com.gnardini.marketoffers.ui.offers

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.Offer
import com.gnardini.marketoffers.ui.show_qr.ShowQrActivity

class AcceptedOffersAdapter(
        private val activity: Activity,
        private val acceptedOffers: List<Offer>)
    : RecyclerView.Adapter<AcceptedOffersAdapter.AcceptedOfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcceptedOfferViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.accepted_offer_view, parent, false)
        return AcceptedOfferViewHolder(view, activity)
    }

    override fun onBindViewHolder(holder: AcceptedOfferViewHolder, position: Int) {
        holder.offer = acceptedOffers[position]
    }

    override fun getItemCount() = acceptedOffers.size

    class AcceptedOfferViewHolder(itemView: View, activity: Activity)
        : RecyclerView.ViewHolder(itemView) {

        private val offerDescription by bindView<TextView>(R.id.description)
        private val generateBarcode by bindView<View>(R.id.generate_barcode)

        var offer: Offer = Offer(-1, "", false)
            set(value) {
                field = value
                offerDescription.text = value.description
            }

        init {
            generateBarcode.setOnClickListener {
                val intent = Intent(activity, ShowQrActivity::class.java)
                intent.putExtra(ShowQrActivity.BARCODE_STRING, offer.id.toString())
                activity.startActivity(intent)
            }
        }
    }

}
