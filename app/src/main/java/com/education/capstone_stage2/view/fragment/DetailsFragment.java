package com.education.capstone_stage2.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.utils.ToolbarUtils;
import com.education.capstone_stage2.view.activity.MapsActivity;
import com.education.capstone_stage2.view.activity.SplashActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class DetailsFragment extends Fragment implements View.OnClickListener {

    public static final String EXTRA_LATITUDE = "latitude";
    public static final String EXTRA_LONGITUDE = "longitude";
    static final String ADS_APP_ID = "ca-app-pub-7839446385475173~3279587199";
    private News news;
    private Toolbar toolbar;
    private TextView tvDescription;
    private ImageView imgVNewsImage;
    private TextView tvDate;
    private ImageView imgNavigate;
    private boolean isTablet;
    private AdView mAdView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getContext(), ADS_APP_ID);

        isTablet = getResources().getBoolean(R.bool.isTablet);
        if (!isTablet) {
            Intent intent = getActivity().getIntent();
            if (intent.hasExtra(HomeFragment.NEWS_ITEM)) {
                news = intent.getParcelableExtra(HomeFragment.NEWS_ITEM);
            }
        } else {
            Bundle bundle = getArguments();
            if (bundle.containsKey(HomeFragment.NEWS_ITEM)) {
                news = bundle.getParcelable(HomeFragment.NEWS_ITEM);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    private void setAdsListener() {
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.e("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.e("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.e("Ads", "onAdClosed");
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        setAdsListener();

        if (!isTablet)
            toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        tvDescription = (TextView) view.findViewById(R.id.tv_details_newsDescription);
        tvDate = (TextView) view.findViewById(R.id.tv_newsDate);
        imgVNewsImage = (ImageView) view.findViewById(R.id.imgV_newsDetails);
        imgNavigate = (ImageView) view.findViewById(R.id.imgV_navigate);


        imgNavigate.setOnClickListener(this);

        if (SplashActivity.isEnglish) {
            if (!isTablet)
                toolbar.setTitle(news.getEnTitle());
            tvDescription.setText(news.getEnLongDescription());
            tvDate.setText(news.getEnDate());
        } else {
            if (!isTablet)
                toolbar.setTitle(news.getArTitle());
            tvDescription.setText(news.getArLongDescription());
            tvDate.setText(news.getArDate());
        }
        if (!isTablet)
            ToolbarUtils.setToolbar((AppCompatActivity) getActivity(),
                    toolbar, true);


    }

    @Override
    public void onClick(View v) {

        if (v == imgNavigate) {
            Intent intent = new Intent(getContext(), MapsActivity.class);
            intent.putExtra(EXTRA_LATITUDE, news.getLatitude());
            intent.putExtra(EXTRA_LONGITUDE, news.getLongitude());
            startActivity(intent);

        }
    }
}
