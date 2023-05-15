package com.theduckfood.services;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.theduckfood.R;
import com.theduckfood.activities.LoginActivity;
import com.theduckfood.activities.MainActivity;
import com.theduckfood.util.Constant;

import java.util.Objects;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FCM", String.format("%s:%s", "New Token:", token));
    }


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("order", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Constant.GENERAL_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(Objects.requireNonNull(message.getNotification()).getTitle())
                .setContentText(message.getNotification().getBody())
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("FCM", "Chưa cấp quyền thông báo");
        }


        notificationManager.notify(101, builder.build());



        if (message.getData().size() > 0) {
            if (Objects.equals(message.getData().get("type"), "order_details")){
                updateOrderDetails(message);
            }
        }
    }

    private void updateOrderDetails(RemoteMessage message) {
        Log.d("FCM", message.getData().get("order_status"));

        Intent intent = new Intent("com.theduckfood.UPDATE_ORDER_STATUS");
        intent.putExtra("order_status", message.getData().get("order_status"));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
