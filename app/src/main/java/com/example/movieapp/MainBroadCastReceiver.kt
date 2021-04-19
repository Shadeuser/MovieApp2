package com.example.movieapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.lang.StringBuilder

class MainBroadCastReceiver: BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        StringBuilder().apply {
            append("Сообщение системы\n")
            if (intent != null) {
                append("Action: ${intent.action}")
                toString().also {
                    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}