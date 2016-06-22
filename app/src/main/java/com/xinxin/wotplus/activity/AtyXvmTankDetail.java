package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.widget.FireWotProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016/6/22.
 * 坦克战斗明细
 */
public class AtyXvmTankDetail extends BaseActivity {

    public static final String TANKID = "TANKID";
    public static final String XVMACTIVETANKS = "XVMACTIVETANKS";

    @BindView(R.id.xvm_tank_detail_appbar)
    AppBarLayout xvm_tank_detail_appbar;
    @BindView(R.id.xvm_tank_detail_toolbar)
    Toolbar xvm_tank_detail_toolbar;
//    @BindView(R.id.recyclerview_xvm_tank_detail)
//    RecyclerView recyclerview_xvm_tank_detail;

    private FireWotProgressDialog firWotProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_tank_detail);
        ButterKnife.bind(this);

        setSupportActionBar(xvm_tank_detail_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("战斗明细");

        Intent intent = getIntent();
        String tankId = intent.getStringExtra(TANKID);
        XvmActiveTanks xvmActiveTanks = (XvmActiveTanks) intent.getSerializableExtra(XVMACTIVETANKS);
        Log.d("++++", xvmActiveTanks.toString());
        Log.d("+++++", tankId);



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
