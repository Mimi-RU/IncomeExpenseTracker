package com.example.incomeexpensetracker.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.incomeexpensetracker.MainActivity
import com.example.incomeexpensetracker.R
import kotlin.random.Random

class PendingTransactionWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker( appContext, workerParams) {

    override suspend fun doWork(): Result {

        // Show Notification (Activity Class will be replaced)
        // prerequisite: Create and register Notification channel with the same channel id first
        // 01 > Activity to Launch
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(applicationContext, 0, intent, 0)


        // 02 > Notification builder
        val channelID: String = "Scheduled_Transactions_Channel_Id"
        val builder = NotificationCompat.Builder(applicationContext, channelID)
            .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
            .setContentTitle("Scheduled Transaction!")
            .setContentText("You have Pending Transactions")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        // 03 > show Notification
        val notificationId = Random.nextInt()
        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationId, builder.build())
        }

        return Result.success()
    }
}