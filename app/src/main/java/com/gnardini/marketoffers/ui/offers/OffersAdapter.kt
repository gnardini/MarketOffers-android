package com.gnardini.marketoffers.ui.offers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gnardini.marketoffers.R
import com.gnardini.marketoffers.kotterknife.bindView
import com.gnardini.marketoffers.model.Offer

class OffersAdapter : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    private var offers : List<Offer> = emptyList()

    fun updateOffers(offers: List<Offer>) {
        this.offers = offers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.offer_view, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.offer = offers[position]
    }

    override fun getItemCount() = offers.size

    class OfferViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        private val offerTitle: TextView by bindView(R.id.offer)
        private val offerPrice: TextView by bindView(R.id.price)

        var offer: Offer = Offer("", 0f)
            set(value) {
                field = value
                offerTitle.text = value.product
                offerPrice.text = String.format("$%.2f", value.price)
            }

        init {
        }
    }

}
