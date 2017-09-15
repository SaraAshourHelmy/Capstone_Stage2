package com.education.capstone_stage2.network;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Sara on 9/15/2017.
 */

public class NewsAPI {

    public static final String ERROR_CODE = "-1";
    private static final String NEWS_URL = "http://www.mocky.io/v2/5185415ba171ea3a00704eed";

    public static String getNewsAPI() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(NEWS_URL)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonResponse = response.body().string();
            return jsonResponse;

        } catch (IOException e) {
            return ERROR_CODE;
        }
    }
}
