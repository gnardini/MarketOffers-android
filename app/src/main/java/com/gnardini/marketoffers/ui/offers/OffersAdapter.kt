package com.gnardini.marketoffers.ui.offers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.Offer
import com.gnardini.marketoffers.repository.BeaconsRepository

class OffersAdapter(private val beaconsRepository: BeaconsRepository)
    : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    private var offers : List<Offer> = emptyList()

    fun updateOffers(offers: List<Offer>) {
        this.offers = offers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.offer_view, parent, false)
        return OfferViewHolder(view, beaconsRepository)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.offer = offers[position]
    }

    override fun getItemCount() = offers.size

    class OfferViewHolder(itemView: View, beaconsRepository: BeaconsRepository)
        : RecyclerView.ViewHolder(itemView) {

        private val offerDescription by bindView<TextView>(R.id.description)
        private val acceptOffer by bindView<View>(R.id.accept_offer)
        private val offerAccepted by bindView<View>(R.id.offer_accepted)

        var offer: Offer = Offer(-1, "", false)
            set(value) {
                field = value
                offerDescription.text = value.description
                if (value.accepted) {
                    acceptOffer.visibility = View.GONE
                    offerAccepted.visibility = View.VISIBLE
                } else {
                    acceptOffer.visibility = View.VISIBLE
                    offerAccepted.visibility = View.GONE
                }
            }

        init {
            acceptOffer.setOnClickListener {
                beaconsRepository.acceptOffer(offer)
                acceptOffer.visibility = View.GONE
                offerAccepted.visibility = View.VISIBLE
            }
        }
    }

}
