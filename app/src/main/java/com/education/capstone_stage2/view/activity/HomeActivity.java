package com.education.capstone_stage2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.utils.FragmentUtility;
import com.education.capstone_stage2.view.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindView();
    }

    private void bindView() {

        HomeFragment homeFragment = new HomeFragment();
        FragmentUtility.insertFragment(this, R.id.containerNews, homeFragment);

    }
}
