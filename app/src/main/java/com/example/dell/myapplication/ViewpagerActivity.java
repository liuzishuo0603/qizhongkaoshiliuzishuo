package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewpagerActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TextView mTvVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTvVp = (TextView) findViewById(R.id.tv_vp);
    }
}
