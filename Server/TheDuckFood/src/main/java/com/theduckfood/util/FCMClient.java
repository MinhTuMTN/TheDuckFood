package com.theduckfood.util;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class FCMClient {
    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";

    public FCMClient() {
    }

    public static boolean sendNotification(String fcmToken, String title, String body) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        JSONObject data = new JSONObject();
        data.put("title", title);
        data.put("body", body);
        JSONObject payload = new JSONObject();
        payload.put("notification", data);
        payload.put("to", fcmToken);

        RequestBody requestBody = RequestBody.create(JSON, payload.toString());
        String apiKey = "AAAA2ckBkhI:APA91bGXyDC77QxstBR13oXoWk-qpl99ZrcdncsH2gwr9S88ZD2BC8vZppfk2956fesyYivkukBemd8Se5quJrwPljKte74aVuk7MRCxRWd1vK8IyJ2PRi51OD0XNjjQQqICk466mya-";
        Request request = new Request.Builder()
                .url(FCM_API_URL)
                .addHeader("Authorization", "key=" + apiKey)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        response.close();
        return response.isSuccessful();
    }
}
