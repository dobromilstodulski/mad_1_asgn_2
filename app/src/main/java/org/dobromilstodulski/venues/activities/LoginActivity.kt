package org.dobromilstodulski.venues.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.dobromilstodulski.venues.databinding.ActivityLoginBinding
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        Timber.i("Login Activity started...")
    }
}