package com.example.justorder.push_notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.justorder.R
import com.example.justorder.ui.restraurant.RestraurantActivity
import com.example.justorder.utils.Constants
import com.google.firebase.messaging.RemoteMessage
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

public class FCMNotificationManager {
    lateinit var mContext : Context
    companion object {
        lateinit var mInstance: FCMNotificationManager
    }
    constructor(context: Context){
        this.mContext=context
    }

    fun getInstance(context: Context):FCMNotificationManager {
        if(mInstance==null){
            mInstance= FCMNotificationManager(context)
        }
        return mInstance
    }

    fun getBitMapFromUrl(strUrl:String ) : Bitmap?{
        try {
            var url=URL(strUrl)
            var connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput=true
            connection.connect()
            var inputStream : InputStream = connection.inputStream
            var myBitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            return myBitmap
        } catch (e:IOException){
            e.printStackTrace()
            return null
        }
    }

    fun displayNotification(remoteMessage: RemoteMessage){
        var bitmap: Bitmap? = getBitMapFromUrl(remoteMessage.notification?.imageUrl.toString())
        var mBuilder : NotificationCompat.Builder = NotificationCompat.Builder(mContext,Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setLargeIcon(bitmap)

        if(bitmap!=null){
            mBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
           // mBuilder.setStyle(NotificationCompat.BigPictureStyle().bigLargeIcon(bitmap))
        }

       mBuilder.setAutoCancel(true)
        var mNotificationManager: NotificationManager = mContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if(mNotificationManager!=null){
            mNotificationManager.notify(1,mBuilder.build())
        }
    }


}