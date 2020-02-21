package com.example.broadcastservices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import java.util.ArrayList

class RecycleVeiwBroadcast(val broadcastCallback: BroadcastCallback) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val messageReceived = intent.getParcelableArrayListExtra<Jojo>("KEY")
        Log.d("TAG","In Received: $messageReceived")
        broadcastCallback.passInfoToRV(messageReceived!!)
    }
}



interface BroadcastCallback{

    fun passInfoToRV(passedValue : ArrayList<Jojo>){


    }



}