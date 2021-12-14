package org.dobromilstodulski.venues.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.dobromilstodulski.venues.databinding.CardVenueBinding
import org.dobromilstodulski.venues.models.VenueModel

interface VenueListener {
    fun onVenueClick(venue: VenueModel)
}

class VenueAdapter constructor(
    private var venues: List<VenueModel>,
    private val listener: VenueListener
) :
    RecyclerView.Adapter<VenueAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardVenueBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val venue = venues[holder.adapterPosition]
        holder.bind(venue, listener)
    }

    override fun getItemCount(): Int = venues.size

    class MainHolder(private val binding: CardVenueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(venue: VenueModel, listener: VenueListener) {
            binding.venueName.text = venue.name
            binding.venueDescription.text = venue.description
            Picasso.get().load(venue.image).resize(200,200).into(binding.venueImage)
            binding.root.setOnClickListener { listener.onVenueClick(venue) }
        }
    }
}
