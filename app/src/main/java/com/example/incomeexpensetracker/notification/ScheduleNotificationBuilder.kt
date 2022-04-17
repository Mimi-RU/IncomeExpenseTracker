package com.example.incomeexpensetracker.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.*
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.incomeexpensetracker.R

class ScheduleNotificationBuilder {

//    val CHANNEL_ID = "123"
//    var builder = NotificationCompat.Builder(
//        this,
//        CHANNEL_ID
//    )
//        .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
//        .setContentTitle("Content Title")
//        .setContentText("Content Text")
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//
//    private fun createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "income_expense_channel"
//            val descriptionText = "Channel Description Text"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//
//    // Create an explicit intent for an Activity in your app
//    val intent = Intent(this, ScheduleNotificationService::class.java).apply {
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//    val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//    val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//        .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
//        .setContentTitle("My notification")
//        .setContentText("Hello World!")
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        // Set the intent that will fire when the user taps the notification
//        .setContentIntent(pendingIntent)
//        .setAutoCancel(true)

}