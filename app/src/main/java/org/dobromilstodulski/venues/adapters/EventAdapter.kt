package org.dobromilstodulski.venues.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.models.EventModel

class EventAdapter(private val eventList : ArrayList<EventModel>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_event,parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = eventList[position]

        holder.Title.text = currentitem.title
        holder.Description.text = currentitem.description

    }

    override fun getItemCount(): Int {

        return eventList.size
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val Title : TextView = itemView.findViewById(R.id.eventTitle)
        val Description : TextView = itemView.findViewById(R.id.eventDescription)

    }
}