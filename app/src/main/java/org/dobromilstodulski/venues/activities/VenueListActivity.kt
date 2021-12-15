package org.dobromilstodulski.venues.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.adapters.VenueAdapter
import org.dobromilstodulski.venues.adapters.VenueListener
import org.dobromilstodulski.venues.databinding.ActivityVenueListBinding
import org.dobromilstodulski.venues.main.MainApp
import org.dobromilstodulski.venues.models.VenueModel

class VenueListActivity : AppCompatActivity(), VenueListener/*, MultiplePermissionsListener*/  {
    lateinit var app: MainApp
    private lateinit var binding: ActivityVenueListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVenueListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadVenues()
        registerRefreshCallback()
        registerMapCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, VenueActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
            R.id.item_map -> {
                val launcherIntent = Intent(this, VenueMapsActivity::class.java)
                mapIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onVenueClick(venue: VenueModel) {
        val launcherIntent = Intent(this, VenueActivity::class.java)
        launcherIntent.putExtra("venue_edit", venue)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadVenues() }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {  }
    }

    private fun loadVenues() {
        showVenues(app.venues.findAll())
    }

    fun showVenues (venues: List<VenueModel>) {
        binding.recyclerView.adapter = VenueAdapter(venues, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}