package com.mindfulhacks.hermoor.model.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mindfulhacks.hermoor.R
import java.lang.Exception

class FirebaseMessagingService : FirebaseMessagingService() {
    //---opening the application on recieving notification---
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try {
            val notificationTitle = remoteMessage.notification!!.title
            val notificationMessage = remoteMessage.notification!!.body
            val clickAction = remoteMessage.notification!!.clickAction
            val fromUserId = remoteMessage.data["from_user_id"]
            //Log.e("from_user_id in FMS is:",from_user_id);

            //----BUILDING NOTIFICATION LAYOUT----
            val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            //--CLICK ACTION IS PROVIDED---
            val resultIntent = Intent(clickAction)
            resultIntent.putExtra("user_id", fromUserId)
            val resultPendingIntent =
                PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            mBuilder.setContentIntent(resultPendingIntent)
            val mNotificationId = System.currentTimeMillis().toInt()
            val mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            mNotifyManager.notify(mNotificationId, mBuilder.build())

            // Log.e("from_user_id 5 is:",from_user_id);
        } catch (e: Exception) {
            Log.e("Exception is ", e.toString())
        }
    }
}