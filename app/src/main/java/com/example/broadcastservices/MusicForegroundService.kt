package com.example.broadcastservices

import android.annotation.TargetApi
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer

@TargetApi(26)
class MusicForegroundService : Service() {


    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.shaanti)
    }

    override fun onStartCommand(intent:Intent, flags:Int, startId:Int):Int {
        val input = intent.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
        0, notificationIntent, 0)
        val serviceIntent = Intent(this, MusicForegroundService::class.java)
        serviceIntent.action = Constants.ACTION.STOP_SERIVCE
        val closeIntent = PendingIntent.getService(this, 0, serviceIntent, 0)

        val notification : Notification =  NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_launcher_foreground,"STOP MUSIC",closeIntent)
            .build()

        startForeground(1, notification)
        mediaPlayer.start()
        if (intent.action.equals(Constants.ACTION.STOP_SERIVCE)) {
            stopService(serviceIntent)}



        return START_NOT_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
    companion object{

       const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onBind(intent: Intent?): IBinder? {

        return null

    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Music Player",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = ContextCompat.getSystemService<NotificationManager>(
                this,NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }














}
