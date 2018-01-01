package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.XvmThirtyDaylistAdapter;
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.listener.HidingScrollListener;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.util.mapper.XvmThirtyInfoToDayMap;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/5/28.
 * 30日战绩列表
 */
public class AtyXvmThirtyRecord extends SwipeBackBaseActivity {

    public static final String TANKS_JS_MAP = "tanks_js_msp";

    @BindView(R.id.xvm_thirtydays_appbar)
    AppBarLayout xvm_thirtydays_appbar;
    @BindView(R.id.xvm_thirtydays_toolbar)
    Toolbar xvm_thirtydays_toolbar;
    @BindView(R.id.xvm_30_collapsing_toolbar)
    CollapsingToolbarLayout xvm_30_collapsing_toolbar;
    @BindView(R.id.recyclerview_xvm_thirtydays)
    RecyclerView recyclerview_xvm_thirtydays;

    @BindView(R.id.fab_tank)
    FloatingActionButton fab_tank;

    private XvmAllInfo xvmAllInfoForOtherPage;
    private XvmThirtyDaylistAdapter adapter;
    // private FireWotProgressDialog firWotProgressDialog;
    private DeathWheelProgressDialog firWotProgressDialog;

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
//            for (Object key : daymap.keySet()) {
//                Object value = daymap.get(key);
//                System.out.println("Key = " + key + ", Value = " + value);
//            }

            adapter = new XvmThirtyDaylistAdapter(AtyXvmThirtyRecord.this, daymap);
            // RecyclerView 动画
            SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerview_xvm_thirtydays);
            recyclerview_xvm_thirtydays.setAdapter(animatorAdapter);

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

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab_tank.getLayoutParams();
        final int fabBottomMargin = lp.bottomMargin;

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview_xvm_thirtydays.setLayoutManager(lm);
        recyclerview_xvm_thirtydays.setItemAnimator(new DefaultItemAnimator());

        recyclerview_xvm_thirtydays.setHasFixedSize(true);
        recyclerview_xvm_thirtydays.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                fab_tank.animate()
                        .translationY(fab_tank.getHeight() + fabBottomMargin)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();
            }

            @Override
            public void onShow() {
                fab_tank.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
        });

        xvm_30_collapsing_toolbar.setTitle("30日数据");
//        setTitle("30日数据");

        Intent intent = this.getIntent();
        // 未使用
        Map tanks = (Map) intent.getSerializableExtra(AtyXvmThirtyRecord.TANKS_JS_MAP);

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        // String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

        firWotProgressDialog = DeathWheelProgressDialog.createDialog(this);
        firWotProgressDialog.show();

        Observable.zip(Network.getXvmInfo().getXvmThirtyDay(woterId),
                Network.getXvmjsApi(1).getTanksjs().map(TanksjsToMapMapper.getInstance()),
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


    /**
     * fab 进入坦克列表页面
     */
    @OnClick(R.id.fab_tank)
    void fab_tank_click() {
        Intent intent = new Intent(this, AtyXvmActiveTank.class);
        startActivity(intent);
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
