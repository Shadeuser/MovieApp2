package com.example.movieapp.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieapp.MainBroadCastReceiver
import com.example.movieapp.R
import com.example.movieapp.databinding.MainFragmentBinding

class MainActivity : AppCompatActivity() {
    lateinit var receiver: MainBroadCastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        receiver = MainBroadCastReceiver()
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}