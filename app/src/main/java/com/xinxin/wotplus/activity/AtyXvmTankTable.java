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
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmTankTable;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.util.mapper.XvmTankTableMap;

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
 * Created by xinxin on 2016/6/5.
 * 单车数据列表
 */
public class AtyXvmTankTable extends BaseActivity {

    @BindView(R.id.xvm_tanktable_appbar)
    AppBarLayout xvm_tanktable_appbar;
    @BindView(R.id.xvm_tanktable_toolbar)
    Toolbar xvm_tanktable_toolbar;
    @BindView(R.id.recyclerview_xvm_tanktable)
    RecyclerView recyclerview_xvm_tanktable;

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
        }

        @Override
        public void onNext(XvmAllInfo xvmAllInfo) {
            Log.d("列表数据源", String.valueOf(xvmAllInfo));
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
        // String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

        // 获取单车数据
        Observable.zip(Network.getXvmInfo().getXvmTankTable(woterId),
                Network.getXvmjsApi().getTanksjs().map(TanksjsToMapMapper.getInstance()),
                new Func2<List<XvmTankTable>, Map, XvmAllInfo>() {
                    @Override
                    public XvmAllInfo call(List<XvmTankTable> xvmTankTables, Map map) {
                        // 合并成ALLINFO
                        XvmAllInfo all = new XvmAllInfo();
                        all.setTankTables(xvmTankTables);
                        all.setTanks(map);
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















