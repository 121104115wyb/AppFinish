package com.btzh.appfinish.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.btzh.appfinish.util.BtzhActivityManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BtzhActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BtzhActivityManager.getInstance().removeActivity(this);
    }





}
