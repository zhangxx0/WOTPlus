package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.XvmThirtyDaylistAdapter;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.util.mapper.XvmThirtyInfoToDayMap;
import com.xinxin.wotplus.widget.FireWotProgressDialog;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/5/28.
 */
public class AtyXvmThirtyRecord extends BaseActivity {

    public static final String TANKS_JS_MAP = "tanks_js_msp";

    @BindView(R.id.xvm_thirtydays_appbar)
    AppBarLayout xvm_thirtydays_appbar;
    @BindView(R.id.xvm_thirtydays_toolbar)
    Toolbar xvm_thirtydays_toolbar;
    @BindView(R.id.recyclerview_xvm_thirtydays)
    RecyclerView recyclerview_xvm_thirtydays;

    private XvmAllInfo xvmAllInfoForOtherPage;
    private XvmThirtyDaylistAdapter adapter;
    private FireWotProgressDialog firWotProgressDialog;

    Observer<XvmAllInfo> thirtyDayObserver = new Observer<XvmAllInfo>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("tank", e.getMessage(), e);
            Snackbar.make(recyclerview_xvm_thirtydays, "获取30日数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(XvmAllInfo xvmAllInfo) {
            Map daymap = xvmAllInfo.getDaymap();
            Log.d("列表数据源", String.valueOf(daymap));
//            for (Object key : daymap.keySet()) {
//                Object value = daymap.get(key);
//                System.out.println("Key = " + key + ", Value = " + value);
//            }

            adapter = new XvmThirtyDaylistAdapter(AtyXvmThirtyRecord.this, daymap);
            recyclerview_xvm_thirtydays.setAdapter(adapter);

            firWotProgressDialog.dismiss();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_thirty_record);
        ButterKnife.bind(this);

        setSupportActionBar(xvm_thirtydays_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview_xvm_thirtydays.setLayoutManager(lm);
        recyclerview_xvm_thirtydays.setItemAnimator(new DefaultItemAnimator());

        setTitle("30日数据");

        Intent intent = this.getIntent();
        // 未使用
        Map tanks = (Map) intent.getSerializableExtra(AtyXvmThirtyRecord.TANKS_JS_MAP);

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        // String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

        firWotProgressDialog = FireWotProgressDialog.createDialog(this);
        firWotProgressDialog.show();

        Observable.zip(Network.getXvmInfo().getXvmThirtyDay(woterId),
                Network.getXvmjsApi().getTanksjs().map(TanksjsToMapMapper.getInstance()),
                new Func2<List<XvmMainInfo.DaylistEntity>, Map, XvmAllInfo>() {
                    @Override
                    public XvmAllInfo call(List<XvmMainInfo.DaylistEntity> daylistEntities, Map map) {
                        // 合并成ALLINFO
                        XvmAllInfo all = new XvmAllInfo();
                        XvmMainInfo main = new XvmMainInfo();
                        main.setDaylist(daylistEntities);
                        all.setXvmMainInfo(main);
                        all.setTanks(map);
                        return all;
                    }
                })
                .map(XvmThirtyInfoToDayMap.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(thirtyDayObserver);

//        Network.getXvmInfo()
//                .getXvmThirtyDay(woterId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(thirtyDayObserver);


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
