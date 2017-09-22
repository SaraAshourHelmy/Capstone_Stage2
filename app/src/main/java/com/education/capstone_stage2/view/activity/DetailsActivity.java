package com.education.capstone_stage2.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.utils.FragmentUtility;
import com.education.capstone_stage2.view.fragment.DetailsFragment;
import com.education.capstone_stage2.view.fragment.HomeFragment;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bindView();
    }

    private void bindView() {

        DetailsFragment detailsFragment = new DetailsFragment();
        FragmentUtility.insertFragment(this, R.id.containerNewsDetails, detailsFragment);

    }

}
