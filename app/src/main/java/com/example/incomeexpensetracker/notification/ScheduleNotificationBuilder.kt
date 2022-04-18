package com.example.incomeexpensetracker.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.incomeexpensetracker.MainActivity
import com.example.incomeexpensetracker.R

class ScheduleNotificationBuilder {

    // variables
    private val channelID: String = "Scheduled_Transactions_Channel_Id"

    // 1. Create/Retrieve Notification Channel
    // Because it's necessary to set a notification channel to deliver notification on android 8.0 or higher,
    // this should happen on application launch or at least before you create notification builder.
    fun createNotificationChannel(context: Context) {
        val name = "SCHEDULED_TRANSACTIONS"
        val descriptionText = "Notification Channel for Scheduled Transactions"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // 2. Set up main Intent for notification
    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    //  3. Build the notification
    private fun getNotification(context: Context, channelID: String): Notification {
        return NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
            .setContentTitle("Schedule Transaction")
            .setContentText("You have Pending Transactions")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent(context = context))
            .setAutoCancel(true)
            .build()
    }

    // 4. Show the Notification
    fun showNotification(context: Context) {
        val notificationId = 1
        val notification = getNotification(context = context, channelID = channelID)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notification)
    }
}