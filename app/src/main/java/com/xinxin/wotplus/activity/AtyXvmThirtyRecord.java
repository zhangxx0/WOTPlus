package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private XvmAllInfo xvmAllInfoForOtherPage;

    Observer<List<XvmMainInfo.DaylistEntity>> thirtyDayObserver = new Observer<List<XvmMainInfo.DaylistEntity>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("tank", e.getMessage(), e);
            Snackbar.make(recyclerview_xvm_thirtydays, "获取30日数据出错！", Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void onNext(List<XvmMainInfo.DaylistEntity> xvmThirtyDayVos) {
            Log.d("成功", String.valueOf(xvmThirtyDayVos.size()));
            Log.d("成功",xvmThirtyDayVos.toString());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_thirty_record);
        ButterKnife.bind(this);

        setSupportActionBar(xvm_thirtydays_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("30日数据");

        Intent intent = this.getIntent();
        xvmAllInfoForOtherPage = (XvmAllInfo) intent.getSerializableExtra("xvmAllInfoForOtherPage");
        Log.d("xvmAllInfoForOtherPage", xvmAllInfoForOtherPage.toString());

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        // String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

        Network.getXvmInfo()
                .getXvmThirtyDay(woterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(thirtyDayObserver);


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
