package com.education.capstone_stage2.network;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Sara on 9/15/2017.
 */

public class NewsService extends IntentService {

    public static final String ACTION_FINISH = "finish";
    public static final String API_RESPONSE = "response";

    public NewsService() {
        super("NewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String response = NewsAPI.getNewsAPI();
        if (!response.equals(NewsAPI.ERROR_CODE)) {
            // todo parse json
            //NavigationManager.navigateToHome(SplashActivity.this);
        }
        notifyFinished(response);
    }

    private void notifyFinished(String response) {
        Intent intent = new Intent(ACTION_FINISH);
        intent.putExtra(API_RESPONSE, response);
        sendBroadcast(intent);
    }
}
