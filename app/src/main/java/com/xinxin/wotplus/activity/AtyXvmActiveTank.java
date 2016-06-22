package com.xinxin.wotplus.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.ActiveTanksMapper;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.widget.FireWotProgressDialog;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/6/22.
 * 活跃坦克列表
 */
public class AtyXvmActiveTank extends BaseActivity {

    @BindView(R.id.xvm_active_tank_appbar)
    AppBarLayout xvm_active_tank_appbar;
    @BindView(R.id.xvm_active_tank_toolbar)
    Toolbar xvm_active_tank_toolbar;
    @BindView(R.id.recyclerview_xvm_active_tank)
    RecyclerView recyclerview_xvm_active_tank;

    private FireWotProgressDialog firWotProgressDialog;


    Observer<XvmActiveTanks> activeTankObserver = new Observer<XvmActiveTanks>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("tank", e.getMessage(), e);
            Snackbar.make(recyclerview_xvm_active_tank, "获取数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(XvmActiveTanks activetanks) {
            Log.d("zzz", activetanks.toString());
            firWotProgressDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_active_tank);
        ButterKnife.bind(this);

        setSupportActionBar(xvm_active_tank_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridLayoutManager gm = new GridLayoutManager(this, 2);
        recyclerview_xvm_active_tank.setLayoutManager(gm);
        recyclerview_xvm_active_tank.setItemAnimator(new DefaultItemAnimator());

        setTitle("活跃战车");

        firWotProgressDialog = FireWotProgressDialog.createDialog(this);
        firWotProgressDialog.show();

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        String name = PreferenceUtils.getCustomPrefString(this, "queryinfo", "name", "");
        String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

//        Network.getXvmInfo()
//                .getXvmThirtyDay(woterId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(activeTankObserver);

        Observable.zip(Network.getXvmInfo().getXvmMainInfo(name, region),
                Network.getXvmjsApi().getTanksjs().map(TanksjsToMapMapper.getInstance()),
                new Func2<XvmMainInfo, Map, XvmActiveTanks>() {
                    @Override
                    public XvmActiveTanks call(XvmMainInfo xvmMainInfo, Map map) {
                        XvmActiveTanks activeTanks = new XvmActiveTanks();
                        activeTanks.setTanks(map);
                        activeTanks.setTanklistEntities(xvmMainInfo.getTanklist());
                        return activeTanks;
                    }
                })
                .map(ActiveTanksMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activeTankObserver);

    }
}
