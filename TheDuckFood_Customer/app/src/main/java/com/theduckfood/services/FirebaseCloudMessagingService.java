package com.theduckfood.services;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FCM", String.format("%s:%s", "New Token:", token));
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d("FCM", "From: " + message.getFrom());
        Log.d("FCM", String.format("%s: %s", Objects.requireNonNull(message.getNotification()).getTitle(), message.getNotification().getBody()));

        // Check if message contains a data payload.
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
