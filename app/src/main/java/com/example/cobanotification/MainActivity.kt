package com.example.cobanotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example"
    private val notificationID = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        btn_button.setOnClickListener {
            sendNotification()
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


    }


    private fun sendNotification(){

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val bitmapLarge = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.dotwo)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.sealonline)

        //notifikasi with Big Picture
        ///////////////////////////////////////////////////

//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Contoh")
//                .setContentText("Deskripsi")
//                .setSmallIcon(R.drawable.sealonline)
//                .setLargeIcon(bitmap)
//                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmapLarge))
//                .setContentIntent(pendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        ////////////////////////////////////////////////////

        //notifikasi with Deskription

        ///////////////////////////////////////////////////

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Contoh")
                .setContentText("Deskripsi")
                .setSmallIcon(R.drawable.sealonline)
                .setLargeIcon(bitmap)
                .setStyle(NotificationCompat.BigTextStyle().bigText("Hello Nama Aku Yudha"))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        ////////////////////////////////////////////////////

        with(NotificationManagerCompat.from(this)){
            builder.setAutoCancel(true)
            notify(notificationID, builder.build())
        }
    }
}