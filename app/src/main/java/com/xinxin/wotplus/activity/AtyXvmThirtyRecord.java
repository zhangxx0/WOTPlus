package com.xinxin.wotplus.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016/5/28.
 *
 */
public class AtyXvmThirtyRecord extends BaseActivity {

    @BindView(R.id.xvm_thirtydays_appbar)
    AppBarLayout xvm_thirtydays_appbar;
    @BindView(R.id.xvm_thirtydays_toolbar)
    Toolbar xvm_thirtydays_toolbar;
    @BindView(R.id.recyclerview_xvm_thirtydays)
    RecyclerView recyclerview_xvm_thirtydays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_thirty_record);
        ButterKnife.bind(this);

        setSupportActionBar(xvm_thirtydays_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("30日数据");




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
