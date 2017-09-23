package com.education.capstone_stage2.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.utils.FragmentUtility;
import com.education.capstone_stage2.utils.LocalUtils;
import com.education.capstone_stage2.utils.ToolbarUtils;
import com.education.capstone_stage2.utils.WidgetUtility;
import com.education.capstone_stage2.view.adapter.NewsAdapter;
import com.education.capstone_stage2.view.fragment.DetailsFragment;
import com.education.capstone_stage2.view.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity implements
        //SharedPreferences.OnSharedPreferenceChangeListener,
        NewsAdapter.RecyclerItemClick {

    public static boolean isTablet;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setChangePreference();
        bindView();
    }

    private void setChangePreference() {
       // PreferenceManager.getDefaultSharedPreferences(this)
         //       .registerOnSharedPreferenceChangeListener(this);
    }

    private void bindView() {
        isTablet = getResources().getBoolean(R.bool.isTablet);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ToolbarUtils.setToolbar(this, toolbar
                , false);

        HomeFragment homeFragment = new HomeFragment();
        FragmentUtility.replaceFragment(this, R.id.containerNews, homeFragment);

    }

    private void setNewsDetailsFragment(News news) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(HomeFragment.NEWS_ITEM, news);
        detailsFragment.setArguments(bundle);
        FragmentUtility.replaceFragment(this, R.id.containerDetails, detailsFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }


    @Override
    public void onItemClick(News news) {
        setNewsDetailsFragment(news);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
