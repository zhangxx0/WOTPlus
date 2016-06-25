package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.XvmActiveTanksAdapter;
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.ActiveTanksMapper;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
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
 * Created by xinxin on 2016/6/22.
 * 活跃坦克列表
 */
public class AtyXvmActiveTank extends SwipeBackBaseActivity {

    @BindView(R.id.xvm_active_tank_appbar)
    AppBarLayout xvm_active_tank_appbar;
    @BindView(R.id.xvm_active_tank_toolbar)
    Toolbar xvm_active_tank_toolbar;
    @BindView(R.id.recyclerview_xvm_active_tank)
    RecyclerView recyclerview_xvm_active_tank;

    private FireWotProgressDialog firWotProgressDialog;
    private XvmActiveTanksAdapter adapter;

    Observer<XvmActiveTanks> activeTankObserver = new Observer<XvmActiveTanks>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Snackbar.make(recyclerview_xvm_active_tank, "获取活跃战车数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(final XvmActiveTanks activetanks) {

            adapter = new XvmActiveTanksAdapter(AtyXvmActiveTank.this, activetanks);

            final List<XvmMainInfo.TanklistEntity> sortedTanks = activetanks.getTanklistEntities();
            adapter.setOnItemClickLitener(new XvmActiveTanksAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    // 点击进入该战车战斗明细页面
                    XvmMainInfo.TanklistEntity tank = sortedTanks.get(position);

                    String tankId = tank.getId().getVehicleTypeCd()+"";

                    Intent intent = new Intent(AtyXvmActiveTank.this, AtyXvmTankDetail.class);
                    if (tank != null) {
                        intent.putExtra(AtyXvmTankDetail.TANKID, tankId);
                        intent.putExtra(AtyXvmTankDetail.XVMACTIVETANKS, activetanks);

                    }
                    startActivity(intent);

                }

                @Override
                public void onItemLongClick(View view, int position) {
                }
            });


            recyclerview_xvm_active_tank.setAdapter(adapter);

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
