package org.dobromilstodulski.venues.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.adapters.EventAdapter
import org.dobromilstodulski.venues.databinding.FragmentEventBinding
import org.dobromilstodulski.venues.databinding.FragmentEventListBinding
import org.dobromilstodulski.venues.models.EventModel

class EventListFragment : Fragment(R.layout.fragment_event_list) {

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!

    private lateinit var reference: DatabaseReference
    private lateinit var eventRecyclerView : RecyclerView
    private lateinit var eventArrayList : ArrayList<EventModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)

        eventArrayList = arrayListOf<EventModel>()

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = EventAdapter(eventArrayList)
       // binding.recyclerView.adapter = PlacemarkAdapter(app.placemarks.findAll(),this)

        getEventData()
        return binding.root
    }

    private fun getEventData() {

        reference = FirebaseDatabase.getInstance().getReference("events")

        reference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val event = userSnapshot.getValue(EventModel::class.java)
                        eventArrayList.add(event!!)

                    }

                    binding.recyclerView.adapter = EventAdapter(eventArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}