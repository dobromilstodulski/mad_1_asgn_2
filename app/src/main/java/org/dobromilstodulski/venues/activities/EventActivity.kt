package org.dobromilstodulski.venues.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityAuthBinding
import org.dobromilstodulski.venues.databinding.ActivityEventBinding
import org.dobromilstodulski.venues.fragments.EventFragment
import org.dobromilstodulski.venues.fragments.EventListFragment
import org.dobromilstodulski.venues.fragments.LoginFragment
import org.dobromilstodulski.venues.fragments.RegisterFragment
import timber.log.Timber
import timber.log.Timber.i

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment= EventListFragment()
        val secondFragment = EventFragment()

        setCurrentFragment(secondFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.list->setCurrentFragment(firstFragment)
                R.id.add->setCurrentFragment(secondFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}