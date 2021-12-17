package org.dobromilstodulski.venues.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.FragmentEventBinding
import org.dobromilstodulski.venues.models.EventModel

class EventFragment : Fragment(R.layout.fragment_event) {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnAddEvent.setOnClickListener() {
            var title = binding.eventTitle.text.toString()
            var description = binding.eventDescription.text.toString()
            var ticket = binding.eventTicket.text.toString()
            var type = binding.eventType.text.toString()
            var time = binding.eventTime.text.toString()
            var date = binding.eventDate.date
            var organiser = binding.eventOrganiser.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty() && ticket.isNotEmpty() &&
                type.isNotEmpty() && time.isNotEmpty() && date.equals(0) &&
                organiser.isNotEmpty())
            {
                database = FirebaseDatabase.getInstance().getReference("events")
                var model = EventModel(title, description, ticket, type, time, date, organiser)
                database.child(title).setValue(model).addOnSuccessListener {
                    binding.eventTitle.text.clear()
                    binding.eventDescription.text.clear()
                    binding.eventTicket.text.clear()
                    binding.eventTime.text.clear()
                    //binding.eventDate.text.clear()
                    binding.eventOrganiser.text.clear()

                    Toast.makeText(activity,"Successfully Saved!",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(activity,"Failed to Save!",Toast.LENGTH_SHORT).show()
                }
            }

            else {
                Toast.makeText(activity, "All Fields Required!", Toast.LENGTH_LONG).show()
            }

        }
    }
}