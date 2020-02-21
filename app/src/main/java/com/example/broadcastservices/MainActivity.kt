package com.example.broadcastservices

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BroadcastCallback {


    override fun passInfoToRV(passedValue: ArrayList<Jojo>) {
        data.clear()
        data.addAll(passedValue)
        Log.d("TAG","In Main Activity: $passedValue")

        adapter.notifyDataSetChanged()

    }

    var data = populateRecyclerVeiw()
    var adapter = JojoRVAdapter(data)

    val recylceBroadcast = RecycleVeiwBroadcast(this)
    val intentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecylcerView()
        intentFilter.addAction("com.example.broadcastservices.recycleveiwbroadcast"
        )

    }


    override fun onStart() {
        super.onStart()
        registerReceiver(recylceBroadcast, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(recylceBroadcast)
    }




    fun startService() {
        val serviceIntent =  Intent(this, MusicForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Playing: Shanti by Roboos")
        ContextCompat.startForegroundService(this,serviceIntent)

    }


    fun onClick(view: View) {

        when(view.id){

            R.id.btnStartService -> startService()


            R.id.btnPopulateRecyclerVeiw -> {
                Intent().also { intent ->
                    intent.setAction("com.example.broadcastservices.recycleveiwbroadcast")
                    intent.putParcelableArrayListExtra("KEY", populateRecyclerVeiw())
                    sendBroadcast(intent)
                }

            }





        }


    }

    fun populateRecyclerVeiw():ArrayList<Jojo>{
        var newReturnList = ArrayList<Jojo>()
        val returnList = ArrayList<Jojo>()
        returnList.add(Jojo("Jonathan","N/A","DIO!","Phantom Blood","jonathon"))
        returnList.add(Jojo("Joseph","Hermit Purple","Your next line is...","Battle Tendency","joseph"))
        returnList.add(Jojo("Jotaro","Star Platinum","Yare yare daze","Stardust Crusaders","jotaro"))
        returnList.add(Jojo("Josuke","Crazy Diamond","What did you say about my hair?!","Diamond Is Unbreakable","josuke"))
        returnList.add(Jojo("Giorno","Gold Experience","I, Gio, have a dream!","Golden Wind","giorno"))
        returnList.add(Jojo("Jolyne","Stone Free","Yare yare dawa","Stone Ocean","jolyne"))
        returnList.add(Jojo("Johnny","N/A","Thank you Gyro","Steel Ball Run","johnny"))
        returnList.add(Jojo("Josefumi","Soft & Wet","I win...completely!","Jojolion","josefumi"))

        var random =  (0..7).random()
        for (i in 0..3){
            newReturnList.add(returnList[random])
            random = (0..7).random()
        }
        return newReturnList
    }

    private fun initRecylcerView(){


        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvJojoList.adapter = adapter
        rvJojoList.layoutManager = layoutManager
        rvJojoList.addItemDecoration(itemDecoration)


    }
}
