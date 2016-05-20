package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.TankAchievesAdapter;
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.model.Achieve;
import com.xinxin.wotplus.model.AchieveTank;
import com.xinxin.wotplus.model.Achievements;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;
import com.xinxin.wotplus.widget.RevealBackgroundView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/8.
 * 坦克详细战绩页面
 */
public class AtyTank extends SwipeBackBaseActivity implements RevealBackgroundView.OnStateChangeListener {

    public static final String TANK_TITLE = "tanktitle";
    public static final String TANK_ID = "tankid";

    private RevealBackgroundView vRevealBackground;
    private DeathWheelProgressDialog deathWheelProgressDialog;

    private CoordinatorLayout tankMainContent;
    private AppBarLayout tankAppbar;
    private NestedScrollView tankScroll;
    private RecyclerView recyclerView;
    private TextView tankdestroy, tankexp, tankmaxexp, tankhitrate, tankhitnum, tankdesdeadrate, tankhitrecirate, tankperdestroy, tankperhitnum;

    private TankAchievesAdapter adapter;
    private Woter woter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank);

        vRevealBackground = (RevealBackgroundView) findViewById(R.id.revealBackgroundView);
        tankAppbar = (AppBarLayout) findViewById(R.id.tank_appbar);
        tankScroll = (NestedScrollView) findViewById(R.id.tank_scroll);
        tankMainContent = (CoordinatorLayout) findViewById(R.id.tank_main_content);
        // 使用这个则全屏变白
        // tankMainContent.setVisibility(View.INVISIBLE);
        // tankAppbar.setVisibility(View.INVISIBLE);
        tankScroll.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String tankTitle = intent.getStringExtra(TANK_TITLE);
        String tankId = intent.getStringExtra(TANK_ID);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tank_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(tankTitle);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_tank_achieves);

        Observer<AchieveTank> tankObserver = new Observer<AchieveTank>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tank", e.getMessage(), e);
                Snackbar.make(tankMainContent, "获取坦克战绩信息出错！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(AchieveTank achieveTank) {

                if (achieveTank != null) {
                    showTankAchieve(achieveTank);
                }

                deathWheelProgressDialog.dismiss();
            }
        };

        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(this);
        deathWheelProgressDialog.show();

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        // 区分南北区
        String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");

        Network.getTankInfo(region)
                .getTankAchieve(woterId, tankId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tankObserver);

        setupRevealBackground(savedInstanceState);

    }

    /**
     *
     * @param achieveTank
     */
    private void showTankAchieve(AchieveTank achieveTank) {

        List<AchieveTank.AchievementsEntity> achieveList = achieveTank.getAchievements();

        // 根据成就的多少，判断展示的行数 <=8 则展示1行，其余展示两行
        int spanCount = 1;
        if (achieveList.size() >= 8) {
            spanCount = 2;
        }
        GridLayoutManager gm = new GridLayoutManager(AtyTank.this, spanCount);
        gm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gm);

        Map map = tomap();

        adapter = new TankAchievesAdapter(achieveList, AtyTank.this, map);
        // RecyclerView 动画
        ScaleInAnimatorAdapter animatorAdapter = new ScaleInAnimatorAdapter(adapter, recyclerView);
        recyclerView.setAdapter(animatorAdapter);

        initCard(achieveTank);
    }

    /**
     * 提取勋章的ID与名称对照字段
     * @return
     */
    private Map tomap() {
        // 从CharedPreference中获取woter
        String woterString = PreferenceUtils.getCustomPrefString(AtyTank.this, "woter", "woterString", "");
        Gson gson2 = new Gson();
        Map<String, String> map = new HashMap<String, String>();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson2.fromJson(woterString, Woter.class);
            // 提取勋章的ID与名称对照字段
            Achievements achievements = woter.getAchievements();
            List<Achieve> warheroList = achievements.getWarheroList();
            for (Achieve achieve : warheroList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
            List<Achieve> honorList = achievements.getHonorList();
            for (Achieve achieve : honorList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
            List<Achieve> epicList = achievements.getEpicList();
            for (Achieve achieve : epicList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
            List<Achieve> teamList = achievements.getTeamList();
            for (Achieve achieve : teamList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
            List<Achieve> commemorateList = achievements.getCommemorateList();
            for (Achieve achieve : commemorateList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
            List<Achieve> stageList = achievements.getStageList();
            for (Achieve achieve : stageList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
            List<Achieve> otherList = achievements.getOtherList();
            for (Achieve achieve : otherList) {
                map.put(achieve.getAchivementId(), achieve.getAchivementName());
            }
        }
        return map;
    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(Constant.START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    Log.d("location", startingLocation.toString());
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
    }

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            // tankMainContent.setVisibility(View.VISIBLE);
            tankAppbar.setVisibility(View.VISIBLE);
            tankScroll.setVisibility(View.VISIBLE);
        }
    }

    // card赋值
    public void initCard(AchieveTank achieveTank) {
        tankdestroy = (TextView) findViewById(R.id.tankdestroy);
        tankexp = (TextView) findViewById(R.id.tankexp);
        tankmaxexp = (TextView) findViewById(R.id.tankmaxexp);
        tankhitrate = (TextView) findViewById(R.id.tankhitrate);
        tankhitnum = (TextView) findViewById(R.id.tankhitnum);
        tankdesdeadrate = (TextView) findViewById(R.id.tankdesdeadrate);
        tankhitrecirate = (TextView) findViewById(R.id.tankhitrecirate);
        tankperdestroy = (TextView) findViewById(R.id.tankperdestroy);
        tankperhitnum = (TextView) findViewById(R.id.tankperhitnum);

        tankdestroy.setText(achieveTank.getFrags_count());
        tankexp.setText(achieveTank.getXp_amount());
        tankmaxexp.setText(achieveTank.getXp_max());
        tankhitrate.setText(achieveTank.getHits_percent());
        tankhitnum.setText(achieveTank.getDamage_dealt());
        tankdesdeadrate.setText(achieveTank.getFrags_killed_ratio());
        tankhitrecirate.setText(achieveTank.getDamage_dealt_received_ratio());
        tankperdestroy.setText(achieveTank.getFrags_per_battle());
        tankperhitnum.setText(achieveTank.getDamage_per_battle());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            // 销毁并返回坦克列表
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
