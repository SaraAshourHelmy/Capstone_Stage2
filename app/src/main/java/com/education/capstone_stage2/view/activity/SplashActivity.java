package com.education.capstone_stage2.view.activity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.db.NewsContract;
import com.education.capstone_stage2.db.NewsQueriesUtils;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.model.NewsDataUtils;
import com.education.capstone_stage2.network.ErrorType;
import com.education.capstone_stage2.network.NewsAPI;
import com.education.capstone_stage2.network.NewsService;
import com.education.capstone_stage2.utils.LocalUtils;
import com.education.capstone_stage2.utils.NavigationManager;
import com.education.capstone_stage2.utils.NetWorkUtility;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    MyReceiver receiver;

    public static ProgressBar loadingProgress;
    private static boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bindView();

    }



    private void bindView() {


        loadingProgress = (ProgressBar) findViewById(R.id.loading);
        saveData();
    }

    private void saveData() {

        if (!flag) {

            receiver = new MyReceiver();
            IntentFilter filter = new IntentFilter(NewsService.ACTION_FINISH);
            registerReceiver(receiver, filter);
            // call API
            if (NetWorkUtility.checkInternetConnection(this)) {
                Intent intent = new Intent(this, NewsService.class);
                startService(intent);
            } else {
                showErrorMessage(ErrorType.No_Connection);
            }

            // todo save data in NewsService
            ArrayList<News> newsLst = NewsDataUtils.getNews();
            for (int i = 0; i < newsLst.size(); i++) {

                ContentValues cv = NewsQueriesUtils.getNewsContentValues(newsLst.get(i));
                Uri uri = getContentResolver().insert(NewsContract.NewsEntry.CONTENT_URI, cv);

            }
            flag = true;
        } else {
            NavigationManager.navigateToHome(SplashActivity.this);
        }


    }

    private void showErrorMessage(ErrorType type) {
        if (type.equals(ErrorType.API)) {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
        } else if (type.equals(ErrorType.No_Connection)) {
            Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG).show();
        }
        finish();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.hasExtra(NewsService.API_RESPONSE)) {
                String response = intent.getStringExtra(NewsService.API_RESPONSE);
                if (response.equals(NewsAPI.ERROR_CODE))
                    showErrorMessage(ErrorType.API);
                else
                    NavigationManager.navigateToHome(SplashActivity.this);
            }
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
        if (receiver != null)
            unregisterReceiver(receiver);
    }
}
