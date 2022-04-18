package com.example.incomeexpensetracker.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.incomeexpensetracker.R

class ScheduleNotificationBuilder {

    // Main steps for building a notification:
    //      0. Get your data
    //      1. Create/Retrieve Notification Channel for O and beyond devices (26+)
    //      2. Build the Text Style
    //      3. Set up main Intent for notification
    //      4. Create additional Actions for the Notification
    //      5. Build and issue the notification

    // variables
    private val channelID: String = "Scheduled_Transactions_Channel_Id"

    // 0. Get your data
    // TODO

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

    //  2. Build the Text Style
    // TODO

    // 3. Set up main Intent for notification
    // TODO

    // 4. Create additional Actions for the Notification
    // TODO

    //  5. Build and issue the notification
    private fun getBuilder(context: Context, channelID: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
            .setContentTitle("Schedule Transaction")
            .setContentText("You have a Pending Transaction")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
    }

    fun showNotification(context: Context) {

        val builder = getBuilder(context = context, channelID = channelID)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(123, builder.build())
        }
    }
}