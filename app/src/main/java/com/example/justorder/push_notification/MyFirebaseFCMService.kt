package com.example.justorder.push_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.content.getSystemService
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseFCMService : FirebaseMessagingService() {

    private val TAG :String= "MYFIREBASESERVICE"
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("FIREBASE", "onNewToken: "+token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.size>0) {}
        Log.e(TAG, "onMessageReceived: "+remoteMessage.data )

        if(remoteMessage.notification!=null){
            Log.e(TAG, "onMessageReceived: "+remoteMessage.notification )
        }

        if (android.os.Build.VERSION.SDK_INT>= android.os.Build.VERSION_CODES.O) {
            var notificationManager=   getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var mChannel = NotificationChannel(com.example.justorder.utils.Constants.CHANNEL_ID,com.example.justorder.utils.Constants.CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH)
            mChannel.description=com.example.justorder.utils.Constants.CHANNEL_DESCRIPTION
            mChannel.enableLights(true)
            mChannel.lightColor= Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern=longArrayOf(100,200,300,400,500,400,300,200,400)
            notificationManager.createNotificationChannel(mChannel)
        }

        var  f :FCMNotificationManager = FCMNotificationManager(this)
        f.displayNotification(remoteMessage)
    }


}