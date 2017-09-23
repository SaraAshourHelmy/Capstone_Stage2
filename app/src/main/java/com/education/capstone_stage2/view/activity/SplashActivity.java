package com.education.capstone_stage2.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.network.ErrorType;
import com.education.capstone_stage2.network.NewsAPI;
import com.education.capstone_stage2.network.NewsService;
import com.education.capstone_stage2.utils.LocalUtils;
import com.education.capstone_stage2.utils.NavigationManager;
import com.education.capstone_stage2.utils.NetWorkUtility;
import com.education.capstone_stage2.utils.SharedUtils;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    MyReceiver receiver;


    public static ProgressBar loadingProgress;
    SharedUtils sharedUtils;
    public static boolean isEnglish = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getLanguage();
        bindView();

    }


    private void bindView() {


        sharedUtils = new SharedUtils(this);
        loadingProgress = (ProgressBar) findViewById(R.id.loading);
        saveData();
    }

    private void saveData() {

        if (sharedUtils.getFirst()) {

            // call API
            if (NetWorkUtility.checkInternetConnection(this)) {
                receiver = new MyReceiver();
                IntentFilter filter = new IntentFilter(NewsService.ACTION_FINISH);
                registerReceiver(receiver, filter);
                Intent intent = new Intent(this, NewsService.class);
                startService(intent);
            } else {
                showErrorMessage(ErrorType.No_Connection);
            }

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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        getLanguage();
        //WidgetUtility.updateWidget(this);
        //Intent intent = getIntent();
        //startActivity(intent);
        //finish();
    }

    public void getLanguage() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        isEnglish = preferences.getBoolean
                (getString(R.string.pref_language_key), true);
        LocalUtils.SetSettingLanguage(this, isEnglish);
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
