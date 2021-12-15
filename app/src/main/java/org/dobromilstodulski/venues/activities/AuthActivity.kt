package org.dobromilstodulski.venues.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityAuthBinding
import org.dobromilstodulski.venues.fragments.LoginFragment
import org.dobromilstodulski.venues.fragments.RegisterFragment
import org.dobromilstodulski.venues.main.MainApp

class AuthActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val firstFragment= LoginFragment()
        val secondFragment = RegisterFragment()

        setCurrentFragment(secondFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.login->setCurrentFragment(firstFragment)
                R.id.register->setCurrentFragment(secondFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}