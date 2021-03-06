package org.dobromilstodulski.venues.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityVenueBinding
import org.dobromilstodulski.venues.helpers.showImagePicker
import org.dobromilstodulski.venues.main.MainApp
import org.dobromilstodulski.venues.models.Location
import org.dobromilstodulski.venues.models.VenueModel
import timber.log.Timber
import timber.log.Timber.i

class VenueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVenueBinding
    var venue = VenueModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    //var location = Location(52.245696, -7.139102, 15f)
    var edit = false
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("Venue Activity started...")

         if (intent.hasExtra("venue_edit")) {
             edit = true
             venue = intent.extras?.getParcelable("venue_edit")!!
             binding.venueTitle.setText(venue.name)
             binding.venueDescription.setText(venue.description)
             binding.venueContact.setText(venue.contact)
             binding.btnAddVenue.setText(R.string.save_venue)
             Picasso.get()
                 .load(venue.image)
                 .into(binding.venueImage)
             if (venue.image != Uri.EMPTY) {
                 binding.btnAddImage.setText(R.string.change_venue_image)
             }
         }

        binding.btnAddVenue.setOnClickListener() {
            venue.name = binding.venueTitle.text.toString()
            venue.description = binding.venueDescription.text.toString()
            venue.contact = binding.venueContact.text.toString()
            venue.type = binding.venueType.toString()
            venue.rating = binding.venueRating.rating.toDouble()
            if (venue.name.isEmpty() && venue.description.isEmpty() && venue.contact.isEmpty() &&
                venue.type.isEmpty() && venue.rating.isNaN()) {
                Snackbar.make(it,R.string.enter_all_venue_details, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.venues.update(venue.copy())
                } else {
                    app.venues.create(venue.copy())
                }
            }
            i("add Button Pressed: $venue")
            setResult(RESULT_OK)
            finish()
        }

        binding.btnAddImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.btnAddLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (venue.zoom != 0f) {
                location.lat =  venue.lat
                location.lng = venue.lng
                location.zoom = venue.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()

        binding.venueType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@VenueActivity, "You Selected: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_venue, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.venues.delete(venue)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            venue.image = result.data!!.data!!
                            Picasso.get()
                                .load(venue.image)
                                .into(binding.venueImage)
                            binding.btnAddImage.setText(R.string.change_venue_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            venue.lat = location.lat
                            venue.lng = location.lng
                            venue.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}