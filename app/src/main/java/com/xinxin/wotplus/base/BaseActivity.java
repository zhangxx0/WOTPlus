package com.xinxin.wotplus.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by xinxin on 2016/3/19.
 * BaseActivity
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 打印Activity名称
        Log.d("BaseActivity", getClass().getSimpleName());

    }
}
