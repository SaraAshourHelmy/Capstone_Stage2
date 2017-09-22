package com.education.capstone_stage2.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.utils.FragmentUtility;
import com.education.capstone_stage2.utils.LocalUtils;
import com.education.capstone_stage2.view.adapter.NewsAdapter;
import com.education.capstone_stage2.view.fragment.DetailsFragment;
import com.education.capstone_stage2.view.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        NewsAdapter.RecyclerItemClick {

    public static boolean isEnglish = true;
    private FrameLayout newsDetailsContainer;
    public static boolean isTablet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getLanguage();
        setChangePreference();
        bindView();
    }

    private void setChangePreference() {
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    private void bindView() {

        newsDetailsContainer = (FrameLayout) findViewById(R.id.containerNewsDetails);
        HomeFragment homeFragment = new HomeFragment();
        FragmentUtility.replaceFragment(this, R.id.containerNews, homeFragment);
        isTablet = getResources().getBoolean(R.bool.isTablet);

    }

    private void setNewsDetailsFragment(News news) {
        newsDetailsContainer.setVisibility(View.VISIBLE);
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(HomeFragment.NEWS_ITEM, news);
        detailsFragment.setArguments(bundle);
        FragmentUtility.replaceFragment(this, R.id.containerNewsDetails, detailsFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }

    public void getLanguage() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        isEnglish = preferences.getBoolean
                (getString(R.string.pref_language_key), true);
        LocalUtils.SetSettingLanguage(this, isEnglish);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        getLanguage();
        recreate();
    }


    @Override
    public void onItemClick(News news) {
        setNewsDetailsFragment(news);
    }
}
