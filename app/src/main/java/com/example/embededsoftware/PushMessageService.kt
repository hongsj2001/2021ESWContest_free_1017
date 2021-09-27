package com.example.embededsoftware

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushMessageService: FirebaseMessagingService() {
    private val TAG = "FirebaseService"
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    //어플 최초 실행 시 파이어베이스의 "Token"으로 토큰을 날려준다
    override fun onNewToken(token: String) {
        Log.d(TAG, "new Token: $token")
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("Token")
        val idByANDROID: String = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)
        databaseReference.child(idByANDROID).setValue(token);
    }

    // 새로운 FCM 메시지가 있을 때 메세지를 받는다
    // 앱이 포어그라운드 상태에서 Notificiation을 받는 경우 푸시 알람을 띄운다.
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.notification != null) {
            sendNotification(remoteMessage.notification?.body, remoteMessage.notification?.title)
        }
        else {
            sendNotification(remoteMessage.notification?.body, remoteMessage.notification?.title)
        }
    }

    // 푸시알람을 어떻게 띄울 것인지에 대한 부가설정 (title,body,알람 소리 등) FCM 메시지를 푸시 알람 형식(head-up 알림)으로 띄운다
    private fun sendNotification(body: String?, title: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("Notification", body)
            putExtra("Notification",title)
        }

        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        val notificationId = 1001
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_HIGH, false,
            getString(R.string.app_name), "App notification channel")

        val channelId = "$packageName-${getString(R.string.app_name)}"

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val fullScreenPendingIntent = PendingIntent.getActivity(baseContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)


        var notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(notificationSound)
            .setContentIntent(pendingIntent)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .setTimeoutAfter(1500)

        notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH
        var notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    // NotificationChannel 만드는 메서드
    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean,
                                          name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}