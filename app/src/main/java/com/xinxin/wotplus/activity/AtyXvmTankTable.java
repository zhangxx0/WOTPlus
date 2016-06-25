package com.xinxin.wotplus.activity;

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
import com.xinxin.wotplus.adapter.XvmTankTableAdapter;
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.model.XvmTankTable;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.util.mapper.XvmTankTableMap;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/6/5.
 * 单车数据列表
 */
public class AtyXvmTankTable extends SwipeBackBaseActivity {

    @BindView(R.id.xvm_tanktable_appbar)
    AppBarLayout xvm_tanktable_appbar;
    @BindView(R.id.xvm_tanktable_toolbar)
    Toolbar xvm_tanktable_toolbar;
    @BindView(R.id.recyclerview_xvm_tanktable)
    RecyclerView recyclerview_xvm_tanktable;

    // private FireWotProgressDialog firWotProgressDialog;
    private DeathWheelProgressDialog firWotProgressDialog;
    private XvmTankTableAdapter adapter;

    Observer<List<XvmTankTable>> tanktableObserver = new Observer<List<XvmTankTable>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("tank", e.getMessage(), e);
            Snackbar.make(recyclerview_xvm_tanktable, "获取单车数据出错！", Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void onNext(List<XvmTankTable> xvmTankTables) {
            Log.d("列表数据源", String.valueOf(xvmTankTables.size()));
            Log.d("列表数据源", String.valueOf(xvmTankTables));
        }
    };

    Observer<XvmAllInfo> tanktableAllObserver = new Observer<XvmAllInfo>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("tank", e.getMessage(), e);
            Snackbar.make(recyclerview_xvm_tanktable, "获取单车数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(XvmAllInfo xvmAllInfo) {

            adapter = new XvmTankTableAdapter(AtyXvmTankTable.this, xvmAllInfo);

            // RecyclerView 动画
            SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerview_xvm_tanktable);
            recyclerview_xvm_tanktable.setAdapter(animatorAdapter);
            firWotProgressDialog.dismiss();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_tank_table);
        ButterKnife.bind(this);

        setSupportActionBar(xvm_tanktable_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview_xvm_tanktable.setLayoutManager(lm);
        recyclerview_xvm_tanktable.setItemAnimator(new DefaultItemAnimator());

        setTitle("单车数据");

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        String name = PreferenceUtils.getCustomPrefString(this, "queryinfo", "name", "");
        String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

        firWotProgressDialog = DeathWheelProgressDialog.createDialog(this);
        firWotProgressDialog.show();

        // 获取单车数据
        Observable.zip(Network.getXvmInfo().getXvmTankTable(woterId),
                Network.getXvmInfo().getXvmMainInfo(name, region),
                Network.getXvmjsApi().getTanksjs().map(TanksjsToMapMapper.getInstance()),
                new Func3<List<XvmMainInfo.TanklistEntity>, XvmMainInfo, Map, XvmAllInfo>() {

                    @Override
                    public XvmAllInfo call(List<XvmMainInfo.TanklistEntity> xvmTankTables, XvmMainInfo xvmMainInfo, Map map) {
                        // 合并成ALLINFO
                        XvmAllInfo all = new XvmAllInfo();
                        all.setTankTables(xvmTankTables);
                        all.setTanks(map);
                        all.setXvmMainInfo(xvmMainInfo);
                        return all;
                    }
                })
                .map(XvmTankTableMap.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tanktableAllObserver);

        // 获取单车数据
//        Network.getXvmInfo()
//                .getXvmTankTable(woterId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(tanktableObserver);

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















