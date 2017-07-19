package com.justinreda.shhelper;

import okhttp3.*;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        ArrayList<String> accountIds = new ArrayList<>();

        accountIds.add("123");
        accountIds.add("234");
        accountIds.add("345");
        accountIds.add("456");

        for (String id: accountIds) {

            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("account_id", id);
                post("http://api.some.url.com/delete_user", jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}