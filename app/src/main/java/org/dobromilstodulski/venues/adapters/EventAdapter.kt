package org.dobromilstodulski.venues.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import org.dobromilstodulski.venues.databinding.CardEventBinding
import org.dobromilstodulski.venues.fragments.EventListFragment
import org.dobromilstodulski.venues.models.EventModel

interface EventListener {
    fun onEventClick(event: EventModel)
}

class EventAdapter(private val events: ArrayList<EventModel>, private val listener: EventListener) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        /*
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_event,parent, false)
        return ViewHolder(itemView)
        */

        val binding = CardEventBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val event = events[holder.adapterPosition]
        holder.bind(event, listener)

        /*
        val currentItem = eventList[position]

        holder.Title.text = currentItem.title
        holder.Description.text = currentItem.description
        holder.Ticket.text = currentItem.ticket
        holder.Type.text = currentItem.type
        holder.Time.text = currentItem.time
        holder.Date.text = currentItem.date
        holder.Organiser.text = currentItem.organiser
        */
    }

    override fun getItemCount(): Int = events.size

    /*
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val Title : TextView = itemView.findViewById(R.id.eventTitle)
        val Description : TextView = itemView.findViewById(R.id.eventDescription)
        val Ticket : TextView = itemView.findViewById(R.id.eventTicket)
        val Type : TextView = itemView.findViewById(R.id.eventType)
        val Time : TextView = itemView.findViewById(R.id.eventTime)
        val Date : TextView = itemView.findViewById(R.id.eventDate)
        val Organiser : TextView = itemView.findViewById(R.id.eventOrganiser)
    }
    */

    class ViewHolder(private val binding : CardEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: EventModel, listener: EventListener) {
            binding.eventTitle.text = event.title
            binding.eventDescription.text = event.description
            binding.root.setOnClickListener { listener.onEventClick(event) }
        }
    }
}