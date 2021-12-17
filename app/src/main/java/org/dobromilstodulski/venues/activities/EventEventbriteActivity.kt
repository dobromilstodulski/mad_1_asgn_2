package org.dobromilstodulski.venues.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityEventEventbriteBinding
import org.dobromilstodulski.venues.main.MainApp

class EventEventbriteActivity : AppCompatActivity() {
    lateinit var app: MainApp
    private lateinit var binding: ActivityEventEventbriteBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityEventEventbriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        //https://developer.android.com/guide/webapps/webview
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl("https://www.eventbrite.ie/d/ireland/events/")
    }
}