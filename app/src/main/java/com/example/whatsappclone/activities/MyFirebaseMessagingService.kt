package com.example.whatsappclone.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.whatsappclone.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelID = "notification_channel"
const val channelName = "com.example.whatsappclone.activities"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    //generate the notification
    //attach the notification created with the custom layout
    //show the notification

    //this method is for showing the message in notification bar
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            generateNotification(
                remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!
            )
        }
    }

    private fun getRemoteView(notificationTitle: String, messageText: String): RemoteViews? {
        val remoteView =
            RemoteViews(
                "com.example.whatsappclone.activities",
                R.layout.notificationbar
            )
        remoteView.setTextViewText(R.id.notificationTitle, notificationTitle)
        remoteView.setTextViewText(R.id.messageText, messageText)
        remoteView.setImageViewResource(R.id.appLogo, R.drawable.pk)
        return remoteView
    }
    
    fun generateNotification(notificationTitle: String, messageText: String) {
        val intent = Intent(this, MainActivity::class.java)
        //this will put MainActivity in the UI
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            intent, PendingIntent.FLAG_ONE_SHOT
        )

        //channel id, channel name
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channelID
        ).setSmallIcon(R.drawable.pk).setAutoCancel(true)
            //vibrate-relax-vibrate-relax
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setContentIntent(pendingIntent)
            .setOnlyAlertOnce(true)

        builder = builder.setContent(getRemoteView(notificationTitle, messageText))

        val notificationManager = builder.setContent(
            getRemoteView(
                notificationTitle,
                messageText
            )
        ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }


}