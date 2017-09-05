package com.damon.appwheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appskeleton.view.activity.ButtonBottomMenuActivity;
import com.example.appskeleton.view.activity.MainActivity;
import com.example.appskeleton.view.base.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void mainactivity(View view){
        startActivity(new Intent(mContext, MainActivity.class));
    }

    public void BottomBarActivity(View view){startActivity(new Intent(mContext, ButtonBottomMenuActivity.class));}
}
