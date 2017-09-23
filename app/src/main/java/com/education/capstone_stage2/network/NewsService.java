package com.education.capstone_stage2.network;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.education.capstone_stage2.db.NewsContract;
import com.education.capstone_stage2.db.NewsQueriesUtils;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.utils.SharedUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("news");
                Log.e("response", response);
                ArrayList<News> lstNew = new ArrayList<>();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<News>>() {
                }.getType();
                lstNew = gson.fromJson(jsonArray.toString(), type);
                for (int i = 0; i < lstNew.size(); i++) {
                    ContentValues cv = NewsQueriesUtils.getNewsContentValues(lstNew.get(i));
                    Uri uri = getContentResolver().insert(NewsContract.NewsEntry.CONTENT_URI, cv);
                }
                SharedUtils sharedUtils = new SharedUtils(this);
                sharedUtils.setFirst(false);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        notifyFinished(response);
    }

    private void notifyFinished(String response) {
        Intent intent = new Intent(ACTION_FINISH);
        intent.putExtra(API_RESPONSE, response);
        sendBroadcast(intent);
    }
}
