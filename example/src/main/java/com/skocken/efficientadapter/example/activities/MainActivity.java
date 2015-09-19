package com.skocken.efficientadapter.example.activities;

import com.skocken.efficientadapter.example.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSimpleList(View view) {
        startActivity(new Intent(this, SimpleListActivity.class));
    }

    public void onClickHeterogeneousList(View view) {
        startActivity(new Intent(this, HeterogeneousListActivity.class));
    }

    public void onClickViewPager(View view) {
        startActivity(new Intent(this, ViewPagerActivity.class));
    }

}
