package com.theduckfood.util;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class FCMClient {
    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";

    public FCMClient() {

    }

    private static boolean sendNotification(String apiKey, String fcmToken, String title, String body) 
        throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        JSONObject data = new JSONObject();
        data.put("title", title);
        data.put("body", body);
        JSONObject payload = new JSONObject();
        payload.put("notification", data);
        payload.put("to", fcmToken);

        RequestBody requestBody = RequestBody.create(JSON, payload.toString());
        Request request = new Request.Builder()
                .url(FCM_API_URL)
                .addHeader("Authorization", "key=" + apiKey)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        response.close();
        return response.isSuccessful();
    }

    public static boolean userSendNotification(String fcmToken, String title, String body) throws IOException {
        return sendNotification(Constants.USER_API_KEY, fcmToken,title, body);
    }

    public static boolean merchantSendNotification(String fcmToken, String title, String body) throws IOException {
        return sendNotification(Constants.MERCHANT_API_KEY, fcmToken,title, body);
    }
}
